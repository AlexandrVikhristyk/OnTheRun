package com.gachigang.ontherun.persistence.repository;

import com.gachigang.ontherun.persistence.entity.Department;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DepartmentRepository extends JpaRepository<Department, Long> {
}
