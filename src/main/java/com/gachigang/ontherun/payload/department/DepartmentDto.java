package com.gachigang.ontherun.payload.department;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.gachigang.ontherun.persistence.entity.Business;
import com.gachigang.ontherun.persistence.entity.User;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.Set;

@AllArgsConstructor
@Getter
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
public class DepartmentDto {

    @NotNull
    Set<User> users;

    @NotNull
    Business business;
}