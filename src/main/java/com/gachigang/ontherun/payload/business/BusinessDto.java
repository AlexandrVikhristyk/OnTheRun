package com.gachigang.ontherun.payload.business;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.annotation.Nullable;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.Set;

import static com.gachigang.ontherun.common.ApplicationConstants.DataValidation.MAX_SIZE_STRING;
import static com.gachigang.ontherun.common.ApplicationConstants.DataValidation.MIN_SIZE_STRING;

@Builder
@AllArgsConstructor
@Getter
@JsonIgnoreProperties(ignoreUnknown = true)
public class BusinessDto {

    @NotBlank
    @Size(min = MIN_SIZE_STRING,
            max = MAX_SIZE_STRING)
    private String name;

    @NotBlank
    @Size(min = MIN_SIZE_STRING,
            max = MAX_SIZE_STRING)
    private String country;

    @Size(min = MIN_SIZE_STRING,
            max = MAX_SIZE_STRING)
    @NotBlank
    private String city;

    @Nullable
    private Set<Long> owners;
}