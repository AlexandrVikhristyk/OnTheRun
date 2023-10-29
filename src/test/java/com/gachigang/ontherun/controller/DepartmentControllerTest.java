package com.gachigang.ontherun.controller;

import com.gachigang.ontherun.common.exception.NotFoundException;
import com.gachigang.ontherun.persistence.entity.Business;
import com.gachigang.ontherun.persistence.entity.User;
import com.gachigang.ontherun.service.DepartmentService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
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
    void testGetDepartmentsByBusinessIdValidId() {
        Business business = Business.builder()
                                    .id(1L)
                                    .build();
        departmentController.getDepartmentsByBusinessId(business.getId());
        verify(departmentService, times(1)).getDepartmentsByBusinessId(business.getId());

        assertDoesNotThrow(() -> departmentController.getDepartmentsByBusinessId(2L));
    }

    @Test
    void testGetDepartmentsByBusinessIdInvalidId() {
        Long businessId = 2L;

        departmentController.getDepartmentsByBusinessId(businessId);

        verify(departmentService, times(1)).getDepartmentsByBusinessId(businessId);
        when(departmentService.getDepartmentsByBusinessId(2L)).thenThrow(new NotFoundException());

        assertThrows(NotFoundException.class, () -> departmentService.getDepartmentsByBusinessId(businessId));
    }

    @Test
    void testGetDepartmentsByBusinessIdNoDepartments() {
        Long businessId = 1L;
        departmentController.getDepartmentsByBusinessId(businessId);

        verify(departmentService, times(1)).getDepartmentsByBusinessId(businessId);
        when(departmentService.getDepartmentsByBusinessId(businessId)).thenThrow(new RuntimeException());

        assertThrows(RuntimeException.class, () -> departmentController.getDepartmentsByBusinessId(businessId));
    }
}
