package com.gachigang.ontherun.config.handler;

import com.gachigang.ontherun.common.exception.NotFoundException;
import com.gachigang.ontherun.common.exception.UserAlreadyExistException;
import com.gachigang.ontherun.model.dto.ErrorMessage;
import io.jsonwebtoken.JwtException;
import jakarta.validation.ConstraintViolationException;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Optional;

/**
 * Exception handler for application.
 */
@Slf4j
@RestControllerAdvice
@RequiredArgsConstructor
public class ApplicationExceptionHandler extends ResponseEntityExceptionHandler {

    private final ExceptionWebHandler exceptionWebHandler;
    private final Environment environment;

    /**
     * Handle AuthenticationExceptions.
     */
    @ExceptionHandler({AuthenticationException.class, JwtException.class})
    public ResponseEntity<ErrorMessage> handleAuthenticationException(@NonNull final Exception exception) {
        log.error("Exception was thrown due authentication error.", exception);
        final Optional<String> responseMessage = Optional.ofNullable(environment.getProperty("unauthorized.error.message"));;
        return exceptionWebHandler.getErrorResponse(exception, HttpStatus.UNAUTHORIZED,
                String.valueOf(responseMessage));
    }

    /**
     * Handle Not found exception.
     */
    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ErrorMessage> handleNotFoundException(@NonNull final NotFoundException exception) {
        log.error("Exception was thrown because the resource was not found.", exception);
        final String responseMessage = environment.getProperty("not.found.error.message");
        return exceptionWebHandler.getErrorResponse(exception, HttpStatus.NOT_FOUND, responseMessage);
    }

    /**
     * Handle ConstraintViolationException.
     */
    @ExceptionHandler({ConstraintViolationException.class, UserAlreadyExistException.class})
    public ResponseEntity<ErrorMessage> handleResourceNotFoundException(@NonNull final Exception exception) {
        log.error("Exception was thrown because request is not valid.", exception);
        final String responseMessage = environment.getProperty("bad.request.error.message");;
        return exceptionWebHandler.getErrorResponse(exception, HttpStatus.BAD_REQUEST, responseMessage);
    }

    /**
     * Handle AccessDeniedException.
     */
    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<ErrorMessage> handleAccessDeniedException(@NonNull final AccessDeniedException exception) {
        log.error("Exception was thrown because access was denied.", exception);
        final String responseMessage = environment.getProperty("access.denied.error.message");;
        return exceptionWebHandler.getErrorResponse(exception, HttpStatus.UNAUTHORIZED,
                responseMessage);
    }

    /**
     * Handle all other exceptions.
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorMessage> handleAllExceptions(@NonNull final Exception exception) {
        log.error("Unknown exception.", exception);
        final var responseMessage = environment.getProperty("internal.server.error.message");;
        return exceptionWebHandler.getErrorResponse(exception, HttpStatus.INTERNAL_SERVER_ERROR,
                responseMessage);
    }
}