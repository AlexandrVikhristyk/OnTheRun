package com.gachigang.ontherun.persistence.repository;

import com.gachigang.ontherun.persistence.entity.Product;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * The {@code ProductRepository} interface defines methods for interacting with
 * {@link Product} entities.
 *<p>
 * This repository allows querying and managing products in the system based on
 * various criteria.
 *
 * @see Product
 */
public interface ProductRepository extends JpaRepository<Product, Long> {
    Boolean existsProductById(Long id);
    List<Product> findAllByCategoryIdAndActiveIsTrue(Long categoryId, Pageable pageable);
    List<Product> findAllByDepartmentId(Long departmentId, Pageable pageable);
    List<Product> findAllByActiveIsFalse(Pageable pageable);
}
