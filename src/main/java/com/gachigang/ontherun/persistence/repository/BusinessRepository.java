package com.gachigang.ontherun.persistence.repository;

import com.gachigang.ontherun.persistence.entity.Business;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Basic repository for the business.
 */
public interface BusinessRepository extends JpaRepository<Business, Long> {
    void deleteBusinessById(Long id);
}
