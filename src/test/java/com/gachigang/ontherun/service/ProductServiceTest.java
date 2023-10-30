package com.gachigang.ontherun.service;

import com.gachigang.ontherun.persistence.entity.Category;
import com.gachigang.ontherun.persistence.entity.Department;
import com.gachigang.ontherun.persistence.entity.Product;
import com.gachigang.ontherun.persistence.repository.CategoryRepository;
import com.gachigang.ontherun.persistence.repository.DepartmentRepository;
import com.gachigang.ontherun.persistence.repository.ProductRepository;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class ProductServiceTest {

    @InjectMocks
    private ProductService productService;

    @Mock
    private ProductRepository productRepository;

    @Mock
    private CategoryRepository categoryRepository;

    @Mock
    private DepartmentRepository departmentRepository;

    @Mock
    private CategoryService categoryService;

    @Mock
    private DepartmentService departmentService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testGetAllByCategory() {
        Long categoryId = 1L;
        Pageable pageable = PageRequest.of(0, 10);
        Product product = new Product();
        product.setId(1L);
        product.setActive(true);

        when(productRepository.findAllByCategoryIdAndActiveIsTrue(categoryId, pageable))
                .thenReturn(Collections.singletonList(product));

        List<Product> result = productService.getAllByCategory(categoryId, pageable);

        assertEquals(1L, result.get(0).getId());
        assertTrue(result.get(0).getActive());
        assertEquals(1, result.size());
        assertEquals(product, result.get(0));
    }

    @Test
    public void testCreateProduct() {
        Product product = Product.builder()
                .categoryId(1L)
                .departmentId(2L)
                .build();

        Category category = Category.builder()
                .id(1L)
                .build();

        Department department = Department.builder()
                .id(2L)
                .build();

        Mockito.when(categoryService.findById(1L)).thenReturn(category);
        Mockito.when(departmentService.findByID(2L)).thenReturn(department);
        Mockito.when(productRepository.save(Mockito.any(Product.class))).thenReturn(product);

        Product createdProduct = productService.createProduct(product);

        Assertions.assertNotNull(createdProduct);
        Assertions.assertEquals(category, createdProduct.getCategory());
        Assertions.assertEquals(department, createdProduct.getDepartment());

        Mockito.verify(categoryService, Mockito.times(1)).findById(1L);
        Mockito.verify(departmentService, Mockito.times(1)).findByID(2L);
        Mockito.verify(productRepository, Mockito.times(1)).save(product);
    }


    @Test
    public void testUpdateProduct() {

        Product existingProduct = Product.builder()
                .id(1L)
                .categoryId(1L)
                .departmentId(2L)
                .build();

        Product updatedProduct = Product.builder()
                .id(1L)
                .categoryId(3L)
                .departmentId(4L)
                .build();

        Category category = Category.builder()
                .id(3L)
                .build();

        Department department = Department.builder()
                .id(4L)
                .build();

        Mockito.when(productRepository.existsProductById(1L)).thenReturn(true);
        Mockito.when(categoryService.findById(3L)).thenReturn(category);
        Mockito.when(departmentService.findByID(4L)).thenReturn(department);
        Mockito.when(productRepository.save(Mockito.any(Product.class))).thenReturn(updatedProduct);

        Product updated = productService.updateProduct(updatedProduct);

        Assertions.assertNotNull(updated);
        Assertions.assertEquals(category, updated.getCategory());
        Assertions.assertEquals(department, updated.getDepartment());

        Mockito.verify(productRepository, Mockito.times(1)).existsProductById(1L);
        Mockito.verify(categoryService, Mockito.times(1)).findById(3L);
        Mockito.verify(departmentService, Mockito.times(1)).findByID(4L);
        Mockito.verify(productRepository, Mockito.times(1)).save(updatedProduct);
    }

    @Test
    public void testUpdateProduct_ProductNotFound() {
        Product nonExistingProduct = Product.builder()
                .id(1L)
                .categoryId(3L)
                .departmentId(4L)
                .build();

        Mockito.when(productRepository.existsProductById(1L)).thenReturn(false);

        Assertions.assertThrows(EntityNotFoundException.class, () -> productService.updateProduct(nonExistingProduct));

        Mockito.verify(productRepository, Mockito.times(1)).existsProductById(1L);
        Mockito.verify(categoryService, Mockito.never()).findById(3L);
        Mockito.verify(departmentService, Mockito.never()).findByID(4L);
        Mockito.verify(productRepository, Mockito.never()).save(Mockito.any(Product.class));
    }

}
