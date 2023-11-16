package com.gachigang.ontherun.payload.business;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.annotation.Nullable;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.Set;

import static com.gachigang.ontherun.common.ApplicationConstants.DataValidation.*;
import static com.gachigang.ontherun.common.ApplicationConstants.DataValidation.MAX_SIZE_STRING;

@Builder
@AllArgsConstructor
@Getter
@JsonIgnoreProperties(ignoreUnknown = true)
public class BusinessDto {

    @NotBlank
    @Size(min = MIN_SIZE_STRING,
            max = MAX_SIZE_STRING)
    String name;

    @NotBlank
    @Size(min = MIN_SIZE_STRING,
            max = MAX_SIZE_STRING)
    String country;

    @Size(min = MIN_SIZE_STRING,
            max = MAX_SIZE_STRING)
    @NotBlank
    String city;

    @Nullable
    Set<Long> owners;
}