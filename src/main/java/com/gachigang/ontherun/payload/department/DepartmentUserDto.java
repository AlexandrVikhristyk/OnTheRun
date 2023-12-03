package com.gachigang.ontherun.payload.department;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Setter;

@AllArgsConstructor
@Setter
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
public class DepartmentUserDto {

    @NotNull
    private String firstname;

    @NotNull
    private String lastname;

    @NotNull
    private String login;

    @NotNull
    private String email;
}
