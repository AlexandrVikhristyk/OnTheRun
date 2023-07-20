package com.gachigang.ontherun.config.handler;

import com.gachigang.ontherun.common.ExceptionMessage;
import com.gachigang.ontherun.common.exception.NotFoundException;
import com.gachigang.ontherun.persistence.entity.ErrorDetails;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.ConstraintViolationException;
import lombok.NonNull;
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
    public ResponseEntity<?> handleAllExceptions(
            @NonNull final Exception exception,
            @NonNull final HttpServletRequest request) {
        log.error("Exception was thrown due unknown exception:", exception);

        final ResponseStatus responseStatus =
                exception.getClass().getAnnotation(ResponseStatus.class);
        final HttpStatus status =
                responseStatus != null ? responseStatus.value() : HttpStatus.INTERNAL_SERVER_ERROR;

        final ErrorDetails message = ErrorDetails.builder()
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
    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ErrorDetails> handleNotFoundException(
            @NonNull final HttpServletRequest request,
            @NonNull final NotFoundException exception) {
        log.error("Exception was thrown because resource was not found:", exception);
        final var message = ErrorDetails.builder()
                .status(HttpStatus.NOT_FOUND.value())
                .timestamp(LocalDateTime.now())
                .description(exceptionMessage.getNotFoundErrorMessage())
                .url(request.getRequestURL().toString())
                .build();
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(message);
    }

    /**
     * Handle ConstraintViolationException.
     */
    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<ErrorDetails> handleResourceNotFoundException(
            @NonNull final HttpServletRequest request,
            @NonNull final Exception exception) {
        log.error("Exception was thrown because passed data was not valid:", exception);
        final var message = ErrorDetails.builder()
                .status(HttpStatus.NOT_FOUND.value())
                .timestamp(LocalDateTime.now())
                .description(exceptionMessage.getNotFoundErrorMessage())
                .url(request.getRequestURL().toString())
                .build();
        return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
    }
}
