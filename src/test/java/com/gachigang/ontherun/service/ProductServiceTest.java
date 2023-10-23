package com.gachigang.ontherun.service;

import com.gachigang.ontherun.persistence.entity.Category;
import com.gachigang.ontherun.persistence.entity.Department;
import com.gachigang.ontherun.persistence.entity.Product;
import com.gachigang.ontherun.persistence.repository.CategoryRepository;
import com.gachigang.ontherun.persistence.repository.DepartmentRepository;
import com.gachigang.ontherun.persistence.repository.ProductRepository;
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
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;

public class ProductServiceTest {

    @InjectMocks
    private ProductService productService;

    @Mock
    private ProductRepository productRepository;

    @Mock
    private CategoryRepository categoryRepository;

    @Mock
    private DepartmentRepository departmentRepository;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testGetAllByCategory() {
        Long categoryId = 1L;
        Pageable pageable = PageRequest.of(0, 10);
        Product product = new Product();
        Mockito.when(productRepository.findAllByCategoryIdAndActiveIsTrue(categoryId, pageable))
                .thenReturn(Collections.singletonList(product));

        List<Product> result = productService.getAllByCategory(categoryId, pageable);

        assertEquals(1, result.size());
        assertEquals(product, result.get(0));
    }

    @Test
    public void testCreateProduct() {

        Product product = new Product();
        product.setActive(true);
        product.setCategoryId(1L);
        product.setDepartmentId(1L);

        Category category = new Category();
        Department department = new Department();

        Mockito.when(categoryRepository.findById(1L)).thenReturn(Optional.of(category));
        Mockito.when(departmentRepository.findById(1L)).thenReturn(Optional.of(department));
        Mockito.when(productRepository.save(product)).thenReturn(product);

        Product result = productService.createProduct(product);

        assertNotNull(result);
        assertEquals(category, result.getCategory());
        assertEquals(department, result.getDepartment());
        assertTrue(result.getActive());
    }


    @Test
    public void testHideProduct() {
        Long productId = 1L;
        Product product = new Product();
        product.setActive(true);

        Mockito.when(productRepository.findById(productId)).thenReturn(Optional.of(product));
        Mockito.when(productRepository.save(product)).thenReturn(product);

        Product result = productService.hideProduct(productId);

        assertFalse(result.getActive());
        verify(productRepository).save(product);
    }

}
