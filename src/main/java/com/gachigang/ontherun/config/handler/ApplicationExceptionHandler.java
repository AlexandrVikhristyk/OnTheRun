package com.gachigang.ontherun.config.handler;

import com.gachigang.ontherun.common.ExceptionMessage;
import com.gachigang.ontherun.payload.ErrorDetails;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.ConstraintViolationException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;

/**
 * Exception handler for application.
 */
@RestControllerAdvice
@RequiredArgsConstructor
@Slf4j
public class ApplicationExceptionHandler extends ResponseEntityExceptionHandler {

    private final ExceptionMessage exceptionMessage;

    /**
     * Handle all other exceptions.
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> handleAllExceptions(Exception exception, HttpServletRequest request) {
        log.error("Exception was thrown due unknown exception:", exception);

        ResponseStatus responseStatus =
                exception.getClass().getAnnotation(ResponseStatus.class);
        HttpStatus status =
                responseStatus != null ? responseStatus.value() : HttpStatus.INTERNAL_SERVER_ERROR;

        ErrorDetails message = ErrorDetails.builder()
                .status(status.value())
                .timestamp(LocalDateTime.now())
                .description(exceptionMessage.getUnauthorizedErrorMessage())
                .url(request.getRequestURL().toString())
                .build();
        return new ResponseEntity<>(message, status);
    }

    /**
     * Handle Not found exception.
     */
    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ErrorDetails> handleNotFoundException(HttpServletRequest request,
                                                                RuntimeException exception) {
        ResponseStatus responseStatus =
                exception.getClass().getAnnotation(ResponseStatus.class);
        HttpStatus status =
                responseStatus != null ? responseStatus.value() : HttpStatus.INTERNAL_SERVER_ERROR;
        log.error("Exception was thrown because resource was not found:", exception);
        ErrorDetails message = ErrorDetails.builder()
                .status(status.value())
                .timestamp(LocalDateTime.now())
                .description(exceptionMessage.getNotFoundErrorMessage())
                .url(request.getRequestURL().toString())
                .build();
        return ResponseEntity.status(status.value()).body(message);
    }

    /**
     * Handle ConstraintViolationException.
     */
    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<ErrorDetails> handleResourceNotFoundException(HttpServletRequest request,
                                                                        Exception exception) {
        ResponseStatus responseStatus =
                exception.getClass().getAnnotation(ResponseStatus.class);
        HttpStatus status =
                responseStatus != null ? responseStatus.value() : HttpStatus.INTERNAL_SERVER_ERROR;
        log.error("Exception was thrown because passed data was not valid:", exception);
        ErrorDetails message = ErrorDetails.builder()
                .status(status.value())
                .timestamp(LocalDateTime.now())
                .description(exceptionMessage.getNotFoundErrorMessage())
                .url(request.getRequestURL().toString())
                .build();
        return ResponseEntity.status(status.value()).body(message);
    }
}