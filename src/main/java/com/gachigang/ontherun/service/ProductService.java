package com.gachigang.ontherun.service;

import com.gachigang.ontherun.persistence.entity.Category;
import com.gachigang.ontherun.persistence.entity.Department;
import com.gachigang.ontherun.persistence.entity.Product;
import com.gachigang.ontherun.persistence.repository.CategoryRepository;
import com.gachigang.ontherun.persistence.repository.DepartmentRepository;
import com.gachigang.ontherun.persistence.repository.ProductRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ProductService {

    private ProductRepository productRepository;
    private CategoryRepository categoryRepository;
    private DepartmentRepository departmentRepository;

    public List<Product> getAllByCategory(Long categoryId, Pageable pageable){
        return productRepository.findAllByCategoryIdAndActiveIsTrue(categoryId, pageable);
    }

    public List<Product> getAllByDepartment(Long departmentId, Pageable pageable){
        return productRepository.findAllByDepartmentId(departmentId, pageable);
    }

    public List<Product> getAllNotActiveProduct(Pageable pageable){
        return productRepository.findAllByActiveIsFalse(pageable);
    }

    public Product createProduct(Product product){
        Category category = categoryRepository.findById(product.getCategoryId())
                .orElseThrow(() -> new EntityNotFoundException("Category not found with id: " + product.getCategoryId()));

        Department department = departmentRepository.findById(product.getDepartmentId())
                .orElseThrow(() -> new EntityNotFoundException("Department not found with id: " + product.getDepartmentId()));

        product.setCategory(category);
        product.setDepartment(department);
        return productRepository.save(product);
    }

    public Product hideProduct(Long id){
        Product product = productRepository.findById(id).orElseThrow(()-> new EntityNotFoundException("Product not found with id: " + id));
            product.setActive(false);
        return productRepository.save(product);
    }
}
