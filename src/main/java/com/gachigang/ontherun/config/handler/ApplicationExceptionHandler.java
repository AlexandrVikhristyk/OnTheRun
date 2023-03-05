package com.gachigang.ontherun.config.handler;

import com.gachigang.ontherun.persistence.entity.ErrorDetails;
import com.gachigang.ontherun.common.ExceptionMessage;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDate;

@ControllerAdvice
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
                .timestamp(LocalDate.now())
                .description(exceptionMessage.getUnauthorizedErrorMessage())
                .url(request.getRequestURL().toString())
                .build();
        return new ResponseEntity<>(message, status);
    }
}
