package com.gachigang.ontherun.common.exception;

public class AuthenticationException extends ApplicationException {
    public AuthenticationException() {
        super("unauthorized.error.message", "Exception was thrown due authentication error.");
    }
}