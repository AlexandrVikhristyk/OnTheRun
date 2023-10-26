package com.gachigang.ontherun.common.exception;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

/**
 * Exception class representing HTTP 404(Not found).
 */
@Component
@PropertySource("classpath:message.properties")
public class NotFoundException extends RuntimeException {

    @Value("${not.found.error.message}")
    private static String notFoundErrorMessage;

    public NotFoundException() {
        super(notFoundErrorMessage);
    }
}
