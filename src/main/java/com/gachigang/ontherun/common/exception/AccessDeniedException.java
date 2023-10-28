package com.gachigang.ontherun.common.exception;

public class AccessDeniedException extends ApplicationException {
    public AccessDeniedException() {
        super("access.denied.error.message", "Exception was thrown because access was denied.");
    }
}