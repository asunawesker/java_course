package com.asunawesker.java.jdbc.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@RequiredArgsConstructor
@AllArgsConstructor
public class Product {
    private Long id;
    private String name;
    private Double price;
    private Date registrationDate;
}
