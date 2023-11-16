package com.gachigang.ontherun.persistence.repository;

import com.gachigang.ontherun.persistence.entity.Business;
import com.gachigang.ontherun.persistence.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * The {@code BusinessRepository} interface defines methods for interacting with
 * {@link Business} entities.
 *
 * <p>This repository provides basic CRUD operations for managing businesses
 * in the system.
 *
 * @see Business
 */
public interface BusinessRepository extends JpaRepository<Business, Long> {

    /**
     * Deletes a business by its ID.
     *
     * @param id The ID of the business to be deleted.
     * @return The deleted business.
     */
    Business deleteBusinessById(Long id);

    /**
     * Finds a list of businesses owned by the specified user.
     *
     * @param user The user for whom to find the businesses.
     * @return The list of businesses owned by the user.
     */
    List<Business> findBusinessByOwners(User user);
}