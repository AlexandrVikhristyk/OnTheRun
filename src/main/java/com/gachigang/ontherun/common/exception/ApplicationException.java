package com.gachigang.ontherun.common.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
@AllArgsConstructor
public class ApplicationException extends RuntimeException {
    private String errorMessage;
    private String logMessage;
}