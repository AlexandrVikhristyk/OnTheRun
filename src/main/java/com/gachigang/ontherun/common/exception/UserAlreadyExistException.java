package com.gachigang.ontherun.common.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Exception that is thrown when a user attempts to register, but
 * the provided user login is already in use.
 */
@ResponseStatus(HttpStatus.BAD_REQUEST)
public class UserAlreadyExistException extends ApplicationException {
    public UserAlreadyExistException() {
        super("user.already.exist.error.message", "Exception was thrown because request is not valid.");
    }
}