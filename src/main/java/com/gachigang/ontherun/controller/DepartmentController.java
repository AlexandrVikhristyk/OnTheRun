package com.gachigang.ontherun.controller;

import com.gachigang.ontherun.common.mapper.DepartmentMapper;
import com.gachigang.ontherun.payload.department.DepartmentDto;
import com.gachigang.ontherun.persistence.entity.Department;
import com.gachigang.ontherun.persistence.entity.User;
import com.gachigang.ontherun.service.DepartmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.gachigang.ontherun.common.exception.NotFoundException;


@RestController
@RequiredArgsConstructor
@RequestMapping("/departments")
public class DepartmentController {

    private final DepartmentService departmentService;
    private final DepartmentMapper departmentMapper;

    /**
     * Endpoint for getting the department of the specific user
     *
     * @return object
     * @throws {@link NotFoundException} if user with id was not found,
     *                or {@link RuntimeException} if there is no department for this user
     */
    @GetMapping("/owning")
    public ResponseEntity<DepartmentDto> getDepartmentByUserId(@AuthenticationPrincipal User user) {
        Department department = departmentService.getDepartmentByUserId(user.getId());
        DepartmentDto response = departmentMapper.departmentToDepartmentDto(department);
        return ResponseEntity.ok(response);
    }
}