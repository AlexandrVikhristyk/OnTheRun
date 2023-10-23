package com.gachigang.ontherun.persistence.entity;

import com.gachigang.ontherun.common.enums.CategoryName;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * Represents a category of products in the application.
 * Each category can contain multiple related products.
 * This class is an entity mapped to the "categories" table in the DB
 */
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private CategoryName name;

    private String description;

    @OneToMany(mappedBy = "category", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Product> products;

    @ElementCollection
    @CollectionTable(name = "category_product_id", joinColumns = @JoinColumn(name = "category_id"))
    @Column(name = "product_id")
    private List<Long> productId;
}