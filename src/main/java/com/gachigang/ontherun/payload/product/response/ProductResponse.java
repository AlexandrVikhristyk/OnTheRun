package com.gachigang.ontherun.payload.product.response;

import lombok.Data;

import java.time.Duration;

@Data
public class ProductResponse {
    private Long id;

    private String name;
    private Double price;
    private Duration cookingTime;
    private String ingredients;
    private Double weight;
    private byte[] image;
    private Integer caloricContent;
    private Boolean deliverable;
    private Boolean active;
    private Boolean absent;
    private Long categoryId;
    private Long departmentId;
}
