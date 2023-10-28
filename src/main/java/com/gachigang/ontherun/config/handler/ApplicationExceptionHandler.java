package com.gachigang.ontherun.config.handler;

import com.gachigang.ontherun.common.exception.AccessDeniedException;
import com.gachigang.ontherun.common.exception.AuthenticationException;
import com.gachigang.ontherun.common.exception.NotFoundException;
import com.gachigang.ontherun.common.exception.UserAlreadyExistException;
import com.gachigang.ontherun.model.dto.ErrorMessage;
import io.jsonwebtoken.JwtException;
import jakarta.validation.ConstraintViolationException;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

/**
 * Exception handler for application.
 */
@Slf4j
@RestControllerAdvice
@RequiredArgsConstructor
@PropertySource("classpath:message.properties")
public class ApplicationExceptionHandler extends ResponseEntityExceptionHandler {

    private final ExceptionWebHandler exceptionWebHandler;
    private final Environment environment;

    /**
     * Handle AuthenticationExceptions.
     */
    @ExceptionHandler({AuthenticationException.class, JwtException.class})
    public ResponseEntity<ErrorMessage> handleAuthenticationException(@NonNull final AuthenticationException exception) {
        log.error(exception.getLogMessage(), exception);
        return exceptionWebHandler.getErrorResponse(exception, HttpStatus.UNAUTHORIZED, exception.getErrorMessage());
    }

    /**
     * Handle Not found exception.
     */
    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ErrorMessage> handleNotFoundException(@NonNull final NotFoundException exception) {
        log.error(exception.getLogMessage(), exception);
        return exceptionWebHandler.getErrorResponse(exception, HttpStatus.NOT_FOUND, exception.getErrorMessage());
    }

    /**
     * Handle ConstraintViolationException.
     */
    @ExceptionHandler({ConstraintViolationException.class, UserAlreadyExistException.class})
    public ResponseEntity<ErrorMessage> handleResourceNotFoundException(@NonNull final UserAlreadyExistException exception) {
        log.error(exception.getLogMessage(), exception);
        return exceptionWebHandler.getErrorResponse(exception, HttpStatus.BAD_REQUEST, exception.getErrorMessage());
    }

    /**
     * Handle AccessDeniedException.
     */
    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<ErrorMessage> handleAccessDeniedException(@NonNull final AccessDeniedException exception) {
        log.error(exception.getLogMessage(), exception);
        return exceptionWebHandler.getErrorResponse(exception, HttpStatus.UNAUTHORIZED, exception.getLogMessage());
    }

    /**
     * Handle all other exceptions.
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorMessage> handleAllExceptions(@NonNull final Exception exception) {
        log.error("Unknown exception.", exception);
        final String responseMessage = environment.getProperty("internal.server.error.message");
        return exceptionWebHandler.getErrorResponse(exception, HttpStatus.INTERNAL_SERVER_ERROR, responseMessage);
    }
}