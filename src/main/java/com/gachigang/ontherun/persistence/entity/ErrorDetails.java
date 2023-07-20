package com.gachigang.ontherun.persistence.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * Class for response object of any Exception in our app.
 */
@Data
@AllArgsConstructor
@Builder
public class ErrorDetails {

    private Integer status;
    private LocalDateTime timestamp;
    private String description;
    private String url;
}