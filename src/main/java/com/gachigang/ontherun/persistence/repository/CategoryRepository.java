package com.gachigang.ontherun.persistence.repository;

import com.gachigang.ontherun.persistence.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {
}
