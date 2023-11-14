package com.gachigang.ontherun.common.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.FORBIDDEN)
public class AccessDeniedException extends ApplicationException {
    public AccessDeniedException() {
        super("access.denied.error.message", "Exception was thrown because access was denied.");
    }
}