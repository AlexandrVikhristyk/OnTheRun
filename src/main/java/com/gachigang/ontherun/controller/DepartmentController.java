package com.gachigang.ontherun.controller;

import com.gachigang.ontherun.persistence.entity.Department;
import com.gachigang.ontherun.persistence.entity.User;
import com.gachigang.ontherun.service.DepartmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.gachigang.ontherun.common.exception.NotFoundException;

import java.util.Set;

@RequiredArgsConstructor
@RestController
@RequestMapping("/departments")
public class DepartmentController {
    private final DepartmentService departmentService;

    /**
     * Endpoint for getting the department of the specific user
     *
     * @return object
     * @throws {@link NotFoundException} if user with id was not found,
     * or {@link RuntimeException} if there is no department for this user
     */
    @GetMapping("/owning")
    public Department getDepartmentByUserId(@AuthenticationPrincipal User user) {
        return departmentService.getDepartmentByUserId(user.getId());
    }

    /**
     * Endpoint for getting the departments of the specific business via id
     *
     * @return A list of {@link Department} objects.
     * @throws {@link NotFoundException} if business with id was not found,
     * or {@link RuntimeException} if there is no departments for this business
     */
    @GetMapping("/{businessId}")
    public Set<Department> getDepartmentsByBusinessId(@PathVariable Long businessId) {
        return departmentService.getDepartmentsByBusinessId(businessId);
    }
}
