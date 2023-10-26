package com.gachigang.ontherun.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

/**
 * Class for response object of any Exception in our app.
 */
@Data
@AllArgsConstructor
@Builder
public class ErrorMessage {

    private int status;
    private String description;
}