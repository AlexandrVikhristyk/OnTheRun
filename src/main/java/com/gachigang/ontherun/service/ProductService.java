package com.gachigang.ontherun.service;

import com.gachigang.ontherun.persistence.entity.Category;
import com.gachigang.ontherun.persistence.entity.Department;
import com.gachigang.ontherun.persistence.entity.Product;
import com.gachigang.ontherun.persistence.repository.ProductRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;
    private final CategoryService categoryService;
    private final DepartmentService departmentService;

    @Transactional(readOnly = true)
    public List<Product> getAllByCategory(Long categoryId, Pageable pageable){
        return productRepository.findAllByCategoryIdAndActiveIsTrue(categoryId, pageable);
    }

    @Transactional(readOnly = true)
    public List<Product> getAllByDepartment(Long departmentId, Pageable pageable){
        return productRepository.findAllByDepartmentId(departmentId, pageable);
    }

    @Transactional(readOnly = true)
    public List<Product> getAllNotActiveProduct(Pageable pageable){
        return productRepository.findAllByActiveIsFalse(pageable);
    }

    @Transactional
    public Product createProduct(Product product){
        Category category = categoryService.findById(product.getCategoryId());
        Department department = departmentService.findByID(product.getDepartmentId());

        product.setCategory(category);
        product.setDepartment(department);
        return productRepository.save(product);
    }

    @Transactional
    public Product hideProduct(Long id){
        Product product = productRepository.findById(id).orElseThrow(()-> new EntityNotFoundException("Product not found with id: " + id));
        product.setActive(false);
        return productRepository.save(product);
    }
}
