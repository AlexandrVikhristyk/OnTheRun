package com.gachigang.ontherun.persistence.repository;

import com.gachigang.ontherun.persistence.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

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
}
