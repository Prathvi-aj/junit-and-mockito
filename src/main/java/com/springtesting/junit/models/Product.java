package com.springtesting.junit.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@Builder
@Entity
public class Product {
    @Id
    private String productId;
    private String name;
    private Double price;

    // Getters and setters
}

