package com.gachigang.ontherun.persistence.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Period;

/**
 * Represents a category entity and defines the schema for creating a table in the database.
 * This class establishes a connection between the class and the "categories" database table
 * using Object-Relational Mapping (ORM) techniques.
 * <p>
 * Categories are used to classify and group products in the application.
 * Each category can contain multiple related products.
 * <p>
 * This class is mapped to the "categories" database table and has a one-to-many
 * relationship with class {@link Product}, allowing multiple products to belong to a category.
 */
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private Double price;
    private Period cookingTime;
    private String ingredients;
    private Double weight;
    private byte[] image;
    private Integer caloricContent;
    private Boolean deliverable;
    private Boolean active;
    private Boolean absent;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    private Category category;

    @Column(name = "category_id", insertable = false, updatable = false)
    private Long categoryId;
}