package com.gachigang.ontherun.common;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

/**
 * The ExceptionMessage class is a component that reads in error messages from a properties file.
 * These error messages are used to provide more
 * informative responses to clients when exceptions are thrown.
 */
@Component
@PropertySource("classpath:message.properties")
@Getter
public class ExceptionMessage {
    @Value("${unauthorized.error.message}")
    private String unauthorizedErrorMessage;

    @Value("${bad.request.error.message}")
    private String badRequestErrorMessage;

    @Value("${not.found.error.message}")
    private String notFoundErrorMessage;
}
