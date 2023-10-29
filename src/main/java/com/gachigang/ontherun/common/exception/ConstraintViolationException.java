package com.gachigang.ontherun.common.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class ConstraintViolationException extends ApplicationException {
    ConstraintViolationException() {
        super("constraint.violation.error.message", "Exception was thrown because request is not valid");
    }
}