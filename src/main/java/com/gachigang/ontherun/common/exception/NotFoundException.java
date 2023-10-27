package com.gachigang.ontherun.common.exception;

/**
 * Exception class representing HTTP 404(Not found).
 */
public class NotFoundException extends RuntimeException {

    public NotFoundException() {
        super("not.found.error.message");
    }
}
