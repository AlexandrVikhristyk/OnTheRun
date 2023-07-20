package com.gachigang.ontherun.common.exception;

import org.springframework.beans.factory.annotation.Value;

/**
 * Exception class representing HTTP 404(Not found).
 */
public class NotFoundException extends RuntimeException {

    @Value("${not.found.error.message}")
    private static String notFoundErrorMessage;

    public NotFoundException() {
        super(notFoundErrorMessage);
    }
}
