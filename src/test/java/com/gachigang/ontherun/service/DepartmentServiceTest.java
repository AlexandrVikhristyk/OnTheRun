package com.gachigang.ontherun.service;

import com.gachigang.ontherun.persistence.entity.Department;
import com.gachigang.ontherun.persistence.entity.User;
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

        assertThrows(IllegalArgumentException.class, () -> departmentService.getDepartmentByUserId(userId));
    }

    @Test
    public void testGetDepartmentByUserIdUserWithNoDepartment() {
        Long userId = 3L;
        User user = User.builder()
                .id(userId)
                .lastname("Doe")
                .build();
        when(userRepository.findById(userId)).thenReturn(Optional.of(user));
        when(departmentRepository.findDepartmentByUsersContains(user)).thenReturn(null);

        assertThrows(RuntimeException.class, () -> departmentService.getDepartmentByUserId(userId));
    }
}

