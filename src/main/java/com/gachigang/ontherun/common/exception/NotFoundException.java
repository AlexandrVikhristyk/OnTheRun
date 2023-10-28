package com.gachigang.ontherun.common.exception;

/**
 * Exception class representing HTTP 404(Not found).
 */
public class NotFoundException extends ApplicationException {
    public NotFoundException() {
        super("not.found.error.message", "Exception was thrown because the resource was not found.");
    }
}