package com.gachigang.ontherun.common.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.UNAUTHORIZED)
public class AuthenticationException extends ApplicationException {
    public AuthenticationException() {
        super("unauthorized.error.message", "Exception was thrown due authentication error.");
    }
}