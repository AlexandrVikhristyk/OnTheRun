package com.gachigang.ontherun.persistence.repository;

import com.gachigang.ontherun.persistence.entity.Department;
import com.gachigang.ontherun.persistence.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * The {@code DepartmentRepository} interface defines methods for interacting with
 * {@link Department} entities.
 *<p>
 * This repository allows querying and managing departments in the system based on
 * various criteria.
 *
 * @see Department
 */
public interface DepartmentRepository extends JpaRepository<Department, Long> {
    Department findDepartmentByUsersContains(User user);
}
