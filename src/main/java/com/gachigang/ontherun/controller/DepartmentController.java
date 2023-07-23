package com.gachigang.ontherun.controller;

import com.gachigang.ontherun.persistence.entity.Department;
import com.gachigang.ontherun.service.DepartmentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/departments")
@Slf4j
public class DepartmentController {
    private final DepartmentService departmentService;

    /**
     * Endpoint for getting the department of the specific user
     *
     * @param id the id of the user to find department
     * @return object
     * @throws {@link IllegalArgumentException} if user with id was not found,
     * or {@link RuntimeException} if there is no department for this user
     */
    @GetMapping("/{id}")
    public Department getDepartmentByUserId(@PathVariable Long id) {
        return departmentService.getDepartmentByUserId(id);
    }
}
