package com.gachigang.ontherun.common.exception;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

/**
 * Exception that is thrown when a user attempts to register, but
 * the provided user login is already in use.
 */
@Component
@PropertySource("classpath:message.properties")
public class UserAlreadyExistException extends RuntimeException {

    @Value("${user.already.exist.error.message}")
    private static String userAlreadyExistErrorMessage;

    public UserAlreadyExistException() {
        super(userAlreadyExistErrorMessage);
    }
}