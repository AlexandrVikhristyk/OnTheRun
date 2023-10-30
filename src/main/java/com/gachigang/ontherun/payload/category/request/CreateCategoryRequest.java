package com.gachigang.ontherun.payload.category.request;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CreateCategoryRequest {
    @NotNull(message = "Write the name")
    private String name;
    private String description;
}
