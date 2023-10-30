package com.gachigang.ontherun.controller;

import com.gachigang.ontherun.common.mapper.CategoryMapper;
import com.gachigang.ontherun.payload.category.request.CreateCategoryRequest;
import com.gachigang.ontherun.payload.category.request.UpdateCategoryRequest;
import com.gachigang.ontherun.payload.category.response.CategoryResponse;
import com.gachigang.ontherun.service.CategoryService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/category")
public class CategoryController {

    private final CategoryService categoryService;
    private final CategoryMapper categoryMapper;

    @GetMapping
    public ResponseEntity<List<CategoryResponse>>findAllCategory(){
        return new ResponseEntity<>(categoryMapper.toDtos(categoryService.findAllCategory()), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity <CategoryResponse> addCategory(@Valid @RequestBody CreateCategoryRequest category){
        return new ResponseEntity<>(categoryMapper.toDto(categoryService.addCategory(categoryMapper.fromDto(category))), HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity <CategoryResponse> updateCategory(@Valid @RequestBody UpdateCategoryRequest category){
        return new ResponseEntity<>(categoryMapper.toDto(categoryService.updateCategory(categoryMapper.fromDto(category))), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public void deleteCategoryById(@PathVariable Long id){
        categoryService.deleteCategory(id);
    }

}
