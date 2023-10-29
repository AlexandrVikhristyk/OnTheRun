package com.gachigang.ontherun.service;

import com.gachigang.ontherun.common.exception.NotFoundException;
import com.gachigang.ontherun.persistence.entity.Business;
import com.gachigang.ontherun.persistence.entity.Department;
import com.gachigang.ontherun.persistence.entity.User;
import com.gachigang.ontherun.persistence.repository.BusinessRepository;
import com.gachigang.ontherun.persistence.repository.DepartmentRepository;
import com.gachigang.ontherun.persistence.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class DepartmentServiceTest {

    @Mock
    private DepartmentRepository departmentRepository;
    @Mock
    private UserRepository userRepository;
    @Mock
    private BusinessRepository businessRepository;

    @InjectMocks
    private DepartmentService departmentService;

    @Test
    public void testGetDepartmentByUserIdExistingUserWithDepartment() {
        Long userId = 1L;
        User user = User.builder()
                .id(userId)
                .lastname("Doe")
                .build();
        Department department = new Department();
        department.setId(1L);
        department.setUsers(Set.of(user));
        when(userRepository.findById(userId)).thenReturn(Optional.of(user));
        when(departmentRepository.findDepartmentByUsersContains(user)).thenReturn(department);

        Department result = departmentService.getDepartmentByUserId(userId);
        assertNotNull(result);
        assertEquals(department, result);
    }

    @Test
    public void testGetDepartmentByUserIdNonExistingUser() {
        Long userId = 2L;
        when(userRepository.findById(userId)).thenReturn(Optional.empty());

        assertThrows(NotFoundException.class, () -> departmentService.getDepartmentByUserId(userId));
    }

    @Test
    public void testGetDepartmentByUserIdUserWithNoDepartment() {
        assertThrows(RuntimeException.class, () -> departmentService.getDepartmentByUserId(4L));
    }

    @Test
    public void testGetDepartmentsByBusinessIdSuccess() {
        Business business = Business.builder()
                .id(1L)
                .build();
        Department department1 = Department.builder()
                .id(1L)
                .business(business)
                .build();
        Department department2= Department.builder()
                .id(2L)
                .business(business)
                .build();
        business.setDepartments(Set.of(department1, department2));

        when(businessRepository.findById(1L)).thenReturn(Optional.of(business));
        when(departmentRepository.findDepartmentsByBusiness(business)).thenReturn(Set.of(department1, department2));

        Set<Department> result = departmentService.getDepartmentsByBusinessId(1L);
        assertNotNull(result);
        assertEquals(business.getDepartments(), result);
    }

    @Test
    public void testGetDepartmentsByBusinessIdInvalidBusinessId() {
        Long invalidBusinessId = 2L;
        when(businessRepository.findById(invalidBusinessId)).thenReturn(Optional.empty());

        assertThrows(IllegalArgumentException.class, () -> departmentService.getDepartmentsByBusinessId(invalidBusinessId));
    }

    @Test
    public void testGetDepartmentsByBusinessIdWithNoDepartments() {
        assertThrows(RuntimeException.class, () -> departmentService.getDepartmentsByBusinessId(1L));
    }
}

