package com.gachigang.ontherun.common.mapper;

import com.gachigang.ontherun.payload.department.DepartmentDto;
import com.gachigang.ontherun.persistence.entity.Department;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

/**
 * This interface defines methods for mapping between the {@link Department} and {@link DepartmentDto} classes.
 */
@Mapper
public interface DepartmentMapper {

    /**
     * Maps a {@link Department} object to a {@link DepartmentDto} object.
     *
     * @param department The {@link Department} object to be mapped.
     * @return The resulting {@link DepartmentDto} object.
     */
    @Mapping(target = "users", ignore = true)
    DepartmentDto departmentToDepartmentDto(Department department);
}