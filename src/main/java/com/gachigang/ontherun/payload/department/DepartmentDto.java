package com.gachigang.ontherun.payload.department;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.gachigang.ontherun.persistence.entity.Business;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@AllArgsConstructor
@Getter
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
public class DepartmentDto {

    @NotNull
    private List<DepartmentUserDto> users;

    @NotNull
    private Business business;
}