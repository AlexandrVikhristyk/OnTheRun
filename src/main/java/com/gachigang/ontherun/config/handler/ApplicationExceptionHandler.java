package com.gachigang.ontherun.config.handler;

import com.gachigang.ontherun.common.exception.ApplicationException;
import com.gachigang.ontherun.common.exception.ConstraintViolationException;
import com.gachigang.ontherun.model.dto.ErrorMessage;
import io.jsonwebtoken.JwtException;
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
     * Handle Not found exception.
     */
    @ExceptionHandler(ApplicationException.class)
    public ResponseEntity<ErrorMessage> handleApplicationException(@NonNull final ApplicationException exception) {
        log.error(exception.getLogMessage(), exception);
        final String responseMessage = environment.getProperty(exception.getErrorMessageKey());
        final HttpStatus responseHttpStatus = exceptionWebHandler.getHttpStatus(exception, HttpStatus.BAD_REQUEST);
        return exceptionWebHandler.getErrorResponse(exception, responseHttpStatus, responseMessage);
    }

    /**
     * Handle ConstraintViolationException.
     */
    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<ErrorMessage> handleResourceNotFoundException(@NonNull final ConstraintViolationException exception) {
        log.error(exception.getLogMessage(), exception);
        final String responseMessage = environment.getProperty(exception.getErrorMessageKey());
        final HttpStatus responseHttpStatus = exceptionWebHandler.getHttpStatus(exception, HttpStatus.BAD_REQUEST);
        return exceptionWebHandler.getErrorResponse(exception, responseHttpStatus, responseMessage);
    }

    /**
     * Handle AuthenticationExceptions.
     */
    @ExceptionHandler(JwtException.class)
    public ResponseEntity<ErrorMessage> jwtException(@NonNull final JwtException exception) {
        log.error("Exception was thrown because the token is not valid", exception);
        final String responseMessage = environment.getProperty("jwt.invalid.token.error.message");
        final HttpStatus responseHttpStatus = exceptionWebHandler.getHttpStatus(exception, HttpStatus.UNAUTHORIZED);
        return exceptionWebHandler.getErrorResponse(exception, responseHttpStatus, responseMessage);
    }

    /**
     * Handle all other exceptions.
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorMessage> handleAllExceptions(@NonNull final Exception exception) {
        log.error("Unknown exception.", exception);
        final String responseMessage = environment.getProperty("internal.server.error.message");
        final HttpStatus responseHttpStatus = exceptionWebHandler.getHttpStatus(exception, HttpStatus.INTERNAL_SERVER_ERROR);
        return exceptionWebHandler.getErrorResponse(exception, responseHttpStatus, responseMessage);
    }
}