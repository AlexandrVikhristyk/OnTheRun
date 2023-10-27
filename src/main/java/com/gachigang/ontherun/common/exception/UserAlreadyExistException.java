package com.gachigang.ontherun.common.exception;

/**
 * Exception that is thrown when a user attempts to register, but
 * the provided user login is already in use.
 */
public class UserAlreadyExistException extends RuntimeException {
    public UserAlreadyExistException() {
        super("user.already.exist.error.message");
    }
}