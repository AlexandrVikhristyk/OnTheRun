package com.gachigang.ontherun.service;

import com.gachigang.ontherun.common.exception.NotFoundException;
import com.gachigang.ontherun.persistence.entity.Department;
import com.gachigang.ontherun.persistence.repository.BusinessRepository;
import com.gachigang.ontherun.persistence.repository.DepartmentRepository;
import com.gachigang.ontherun.persistence.repository.UserRepository;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;

@Service
@RequiredArgsConstructor
public class DepartmentService {

    private final DepartmentRepository departmentRepository;
    private final UserRepository userRepository;
    private final BusinessRepository businessRepository;

    @Transactional(readOnly = true)
    public Department getDepartmentByUserId(@NonNull final Long id) {
        return departmentRepository.findDepartmentByUsersContains(userRepository.findById(id)
                .orElseThrow(NotFoundException::new));
    }

    @Transactional(readOnly = true)
    public Set<Department> getDepartmentsByBusinessId(@NonNull Long businessId) {
        return departmentRepository.findDepartmentsByBusiness(businessRepository.findById(businessId)
                .orElseThrow(NotFoundException::new));
    }
}
