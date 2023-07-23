package com.gachigang.ontherun.controller;

import com.gachigang.ontherun.service.DepartmentService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class DepartmentControllerTest {
    @Mock
    private DepartmentService departmentService;

    @InjectMocks
    private DepartmentController departmentController;

    @Test
    void getDepartmentByUserId() {
        Long userId = 1L;
        departmentController.getDepartmentByUserId(userId);
        verify(departmentService, times(1)).getDepartmentByUserId(userId);
    }
}
