package com.gachigang.ontherun.persistence.repository;

import com.gachigang.ontherun.persistence.entity.Department;
import com.gachigang.ontherun.persistence.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Basic repository for the department.
 */
public interface DepartmentRepository extends JpaRepository<Department, Long> {
    Department findDepartmentByUsersContains(User user);
}
