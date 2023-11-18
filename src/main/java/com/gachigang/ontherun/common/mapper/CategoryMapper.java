package com.gachigang.ontherun.common.mapper;

import com.gachigang.ontherun.payload.category.request.CreateCategoryRequest;
import com.gachigang.ontherun.payload.category.request.UpdateCategoryRequest;
import com.gachigang.ontherun.payload.category.response.CategoryResponse;
import com.gachigang.ontherun.persistence.entity.Category;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper
public interface CategoryMapper {
    CategoryResponse toDto(Category entity);
    List<CategoryResponse> toDtos(List<Category> entities);
    Category fromDto(CreateCategoryRequest dto);
    Category fromDto(UpdateCategoryRequest dto);

}
