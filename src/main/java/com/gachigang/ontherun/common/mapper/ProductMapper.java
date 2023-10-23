package com.gachigang.ontherun.common.mapper;

import com.gachigang.ontherun.payload.product.request.CreateProductRequest;
import com.gachigang.ontherun.payload.product.response.ProductResponse;
import com.gachigang.ontherun.persistence.entity.Product;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper
public interface ProductMapper {
    Product fromDto(CreateProductRequest dto);
    ProductResponse toDto(Product entity);
    List<ProductResponse> toDtos(List<Product> entities);
}
