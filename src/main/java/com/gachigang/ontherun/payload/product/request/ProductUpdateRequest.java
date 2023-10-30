package com.gachigang.ontherun.payload.product.request;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Duration;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProductUpdateRequest {
    @NotNull(message = "Id cannot be null")
    private Long id;
    @NotNull(message = "Name cannot be null.")
    private String name;
    @NotNull(message = "Price cannot be null.")
    private Double price;
    @NotNull(message = "Type time of cooking in minutes.")
    private Duration cookingTime;
    @NotNull(message = "Write ingredients")
    private String ingredients;
    @NotNull(message = "Write caloric")
    private Integer caloricContent;
    @NotNull(message = "Weight cannot be null.")
    private Double weight;
    @NotNull(message = "Upload the image.")
    private byte[] image;
    @NotNull(message = "Write if this product can be delivered")
    private Boolean deliverable;
    @NotNull(message = "Is this product active?")
    private Boolean active;
    @NotNull(message = "Do we have this product?")
    private Boolean absent;
    @NotNull(message = "Write the id of category")
    private Long categoryId;
    @NotNull(message = "Write the id of department")
    private Long departmentId;
}
