package com.gachigang.ontherun.common.mapper;

import com.gachigang.ontherun.model.dto.DepartmentDto;
import com.gachigang.ontherun.persistence.entity.Department;
import org.mapstruct.Mapper;

/**
 * This interface defines methods for mapping between the {@link Department} and {@link DepartmentDto} classes.
 */
@Mapper
public interface DepartmentMapper {
    /** Maps a {@link DepartmentDto} object to a {@link  Department} object.
     *
     * @param dto The {@link DepartmentDto} object to be mapped.
     * @return The resulting {@link Department} object.
     */
    Department fromDto(DepartmentDto dto);

    /** Maps a {@link Department} object to a {@link  DepartmentDto} object.
     *
     * @param department The {@link Department} object to be mapped.
     * @return The resulting {@link DepartmentDto} object.
     */
    DepartmentDto toDto(Department department);
}
