package com.asunawesker.java.jdbc.jdbc.repository;

import com.asunawesker.java.jdbc.jdbc.model.Category;
import com.asunawesker.java.jdbc.jdbc.util.DatabaseConnection;

import javax.swing.plaf.nimbus.State;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CategoryRepository implements Repository<Category>{

    private Connection getConnection() throws SQLException {
        return DatabaseConnection.getConnection();
    }

    @Override
    public List<Category> getAll() {
        List<Category> categories = new ArrayList<>();
        try(Statement statement = getConnection().createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM categories")){
            while(resultSet.next()){
                categories.add(createCategory(resultSet));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return categories;
    }

    @Override
    public Category getById(Long id) {
        Category category = new Category();
        try(PreparedStatement statement = getConnection().prepareStatement("SELECT * FROM categories WHERE id = ?")){
            statement.setLong(1, id);
            try(ResultSet resultSet = statement.executeQuery();){
                if(resultSet.next()){
                    category = createCategory(resultSet);
                }
            }
        } catch (SQLException e){
            throw new RuntimeException(e);
        }
        return category;
    }

    @Override
    public Category save(Category category) {
        String sql = null;
        if(category.getId() != null && category.getId() > 0){
            sql = "UPDATE categories SET name = ? WHERE id = ?";
        } else {
            sql = "INSERT INTO categories (name) VALUES (?)";
        }
        try(PreparedStatement statement = getConnection().prepareStatement(sql)){
            statement.setString(1, category.getName());
            if(category.getId() != null && category.getId() > 0){
                statement.setLong(2, category.getId());
            }
            statement.executeUpdate();

            if(category.getId() == null){
                try(ResultSet resultSet = statement.getGeneratedKeys()){
                    if(resultSet.next()){
                        category.setId(resultSet.getLong(1));
                    }
                }
            }

            return category;
        } catch(SQLException e){
            throw new RuntimeException(e);
        }
    }

    @Override
    public void delete(Long id) {
        try(
            PreparedStatement statement =
                getConnection().prepareStatement("DELETE FROM categories WHERE id = ?");){
            statement.setLong(1, id);
            statement.executeUpdate();
        } catch(SQLException e){
            throw new RuntimeException(e);
        }
    }

    private Category createCategory(ResultSet resultSet) throws SQLException {
        return new Category(resultSet.getLong("id"), resultSet.getString("name"));
    }
}
