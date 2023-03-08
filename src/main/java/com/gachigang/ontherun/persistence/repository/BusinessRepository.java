package com.gachigang.ontherun.persistence.repository;

import com.gachigang.ontherun.persistence.entity.Business;
import com.gachigang.ontherun.persistence.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Basic repository for the business.
 */
import java.util.List;

public interface BusinessRepository extends JpaRepository<Business, Long> {
    void deleteBusinessById(Long id);

    List<Business> findBusinessByOwners(User user);
}
