package com.gachigang.ontherun.persistence.repository;

import com.gachigang.ontherun.persistence.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * The {@code CategoryRepository} interface defines methods for interacting with
 * {@link Category} entities.
 *
 * <p>This repository allows querying and managing categories in the system based on
 * various criteria.
 *
 * @see Category
 */
public interface CategoryRepository extends JpaRepository<Category, Long> {
}
