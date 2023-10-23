package com.gachigang.ontherun.controller;

import com.gachigang.ontherun.common.mapper.ProductMapper;
import com.gachigang.ontherun.payload.product.request.CreateProductRequest;
import com.gachigang.ontherun.payload.product.response.ProductResponse;
import com.gachigang.ontherun.service.ProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/product")
public class ProductController {

    private final ProductService productService;
    private final ProductMapper productMapper;


    @GetMapping("/{id}")
    public ResponseEntity <List<ProductResponse>> getAllByCategory(
                                          @PathVariable(name = "id") Long categoryId,
                                          @RequestParam(required = false, defaultValue = "0") int pageNumber,
                                          @RequestParam(required = false, defaultValue = "3") int pageSize){
        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        return new ResponseEntity<>(productMapper.toDtos(productService.getAllByCategory(categoryId, pageable)), HttpStatus.OK);
    }

    @GetMapping("/productOfDepartment/{id}")
    public ResponseEntity <List<ProductResponse>> getAllByDepartment(
                                          @PathVariable(name = "id") Long departmentId,
                                          @RequestParam(required = false, defaultValue = "0") int pageNumber,
                                          @RequestParam(required = false, defaultValue = "3") int pageSize){
        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        return new ResponseEntity<>(productMapper.toDtos(productService.getAllByDepartment(departmentId, pageable)), HttpStatus.OK);
    }

    @GetMapping("/notActive")
    public ResponseEntity<List<ProductResponse>> getAllNotActiveProduct(
                                          @RequestParam(required = false, defaultValue = "0") int pageNumber,
                                          @RequestParam(required = false, defaultValue = "3") int pageSize){
        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        return new ResponseEntity<>(productMapper.toDtos(productService.getAllNotActiveProduct(pageable)), HttpStatus.OK);
    }


    @PostMapping
    public ResponseEntity<ProductResponse> createProduct (@Valid @RequestBody CreateProductRequest product){
        return new ResponseEntity<>(productMapper.toDto(productService.createProduct(productMapper.fromDto(product))), HttpStatus.OK);
    }

    @PutMapping("/hide/{id}")
    public ResponseEntity <ProductResponse> hideProduct(@PathVariable Long id){
        return new ResponseEntity<>(productMapper.toDto(productService.hideProduct(id)), HttpStatus.OK);
    }
}

