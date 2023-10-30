package com.gachigang.ontherun.service;

import com.gachigang.ontherun.persistence.entity.Category;
import com.gachigang.ontherun.persistence.repository.CategoryRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryService {
    private final CategoryRepository categoryRepository;

    @Transactional(readOnly = true)
    public List<Category> findAllCategory(){
        return categoryRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Category findById(Long id){
        return categoryRepository.findById(id)
                .orElseThrow(()-> new EntityNotFoundException("Category with id " + id + " not found"));
    }

    @Transactional
    public Category addCategory(Category category){
        return categoryRepository.save(category);
    }

    @Transactional
    public Category updateCategory(Category category){
        if (!categoryRepository.existsCategoryById(category.getId())){
            throw new EntityNotFoundException("Category Not Found");
        }
        return categoryRepository.save(category);
    }
    @Transactional
    public void deleteCategory(Long id){
        categoryRepository.deleteById(id);
    }
}
