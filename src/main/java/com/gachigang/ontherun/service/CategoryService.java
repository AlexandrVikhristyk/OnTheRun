package com.gachigang.ontherun.service;

import com.gachigang.ontherun.persistence.entity.Category;
import com.gachigang.ontherun.persistence.repository.CategoryRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class CategoryService {
    private CategoryRepository categoryRepository;

    public List<Category> findAllCategory(Pageable pageable){
        Page<Category> page = categoryRepository.findAll(pageable);
        return page.getContent();
    }
    public Category addCategory(Category category){
        return categoryRepository.save(category);
    }

    public Category updateCategory(Category category){
        Optional<Category> existingCategory = categoryRepository.findById(category.getId());
        if (existingCategory.isPresent()){
        return categoryRepository.save(category);
        }

        else{
            throw new EntityNotFoundException("Category Not Found");
        }
    }
    public void deleteCategory(Long id){
        categoryRepository.deleteById(id);
    }
}
