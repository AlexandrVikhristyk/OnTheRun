package com.gachigang.ontherun.common.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Exception class representing HTTP 404(Not found).
 */
@ResponseStatus(HttpStatus.NOT_FOUND)
public class NotFoundException extends ApplicationException {
    public NotFoundException() {
        super("not.found.error.message", "Exception was thrown because the resource was not found.");
    }
}