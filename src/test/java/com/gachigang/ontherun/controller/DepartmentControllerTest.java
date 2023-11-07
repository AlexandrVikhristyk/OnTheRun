package com.gachigang.ontherun.controller;

import com.gachigang.ontherun.persistence.entity.Business;
import com.gachigang.ontherun.persistence.entity.User;
import com.gachigang.ontherun.service.DepartmentService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class DepartmentControllerTest {
    @Mock
    private DepartmentService departmentService;

    @InjectMocks
    private DepartmentController departmentController;

   @Test
    void testGetDepartmentByUserId() {
        User user = User.builder()
                        .id(1L)
                        .lastname("test")
                        .build();
        departmentController.getDepartmentByUserId(user);
        verify(departmentService, times(1)).getDepartmentByUserId(user.getId());
    }

    @Test
    void testGetDepartmentsByBusinessId() {
        Business business = Business.builder()
                                    .id(1L)
                                    .build();
        departmentController.getDepartmentsByBusinessId(business.getId());
        verify(departmentService, times(1)).getDepartmentsByBusinessId(business.getId());
    }
}
