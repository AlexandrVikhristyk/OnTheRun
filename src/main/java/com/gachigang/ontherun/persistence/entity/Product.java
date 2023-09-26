package com.gachigang.ontherun.persistence.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

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
    private LocalDateTime cookedAt;
    private String ingredients;
    private Double weight;
    private byte[] image;
    private Integer caloricContent;
    private Boolean deliverable;
    private Boolean active;
    private Boolean absent;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;
}
