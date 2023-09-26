package com.gachigang.ontherun.persistence.repository;

import com.gachigang.ontherun.persistence.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
