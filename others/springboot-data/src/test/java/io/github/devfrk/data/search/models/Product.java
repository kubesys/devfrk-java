/**
 * Copyright (2019, ) Institute of Software, Chinese Academy of Sciences
 */
package io.github.devfrk.data.search.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

import lombok.Data;

@Data
@Document(indexName = "product")
public class Product {
    @Id
    public Long id;
    
    public String name;
    
    public double price;
    
    public int quantity;

    // getters and setters
}
