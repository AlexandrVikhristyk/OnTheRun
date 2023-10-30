package com.gachigang.ontherun.payload.category.request;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class UpdateCategoryRequest {
    @NotNull(message = "Id cannot be null")
    private Long id;
    @NotNull(message = "Name cannot be null")
    private String name;
    @NotNull(message = "Description cannot be null")
    private String description;
}
