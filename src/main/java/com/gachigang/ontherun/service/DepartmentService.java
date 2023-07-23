package com.gachigang.ontherun.service;

import com.gachigang.ontherun.persistence.entity.Department;
import com.gachigang.ontherun.persistence.repository.DepartmentRepository;
import com.gachigang.ontherun.persistence.repository.UserRepository;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import org.springframework.stereotype.Service;


@AllArgsConstructor
@Service
public class DepartmentService {
    private final DepartmentRepository departmentRepository;
    private final UserRepository userRepository;

    public Department getDepartmentByUserId(@NonNull final Long id) {
        Department foundDepartment = departmentRepository.findDepartmentByUsersContains(userRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("User with id " + id + " not found")));
        if(foundDepartment == null) {
            throw new RuntimeException("There is no department for this user");
        }
        else return foundDepartment;
    }
}
