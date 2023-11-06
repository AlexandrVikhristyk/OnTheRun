package com.gachigang.ontherun.model.dto;

import com.gachigang.ontherun.persistence.entity.Business;
import com.gachigang.ontherun.persistence.entity.Department;
import com.gachigang.ontherun.persistence.entity.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

/**
 *  DTO class to represent {@link Department}.
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DepartmentDto {
    private Set<User> users;
    private Business business;
}
