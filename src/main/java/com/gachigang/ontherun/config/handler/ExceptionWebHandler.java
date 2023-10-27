package com.gachigang.ontherun.config.handler;

import com.gachigang.ontherun.model.dto.ErrorMessage;
import jakarta.annotation.Nonnull;
import lombok.NonNull;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ResponseStatus;

@Component
public class ExceptionWebHandler {

    public HttpStatus getHttpStatus(@Nonnull final Exception exception,
                                    @Nonnull final HttpStatus status) {
        final ResponseStatus responseStatus = exception.getClass().getAnnotation(ResponseStatus.class);
        return responseStatus != null ? responseStatus.value() : status;
    }

    public ResponseEntity<ErrorMessage> getErrorResponse(@NonNull final Exception exception,
                                                         @Nonnull final HttpStatus fallBackHttpStatus,
                                                         @Nonnull final String responseMessage) {
        final HttpStatus httpStatus = getHttpStatus(exception, fallBackHttpStatus);
        final ErrorMessage message = getErrorMessage(httpStatus, responseMessage);
        return new ResponseEntity<>(message, httpStatus);
    }

    public ErrorMessage getErrorMessage(@Nonnull final HttpStatus httpStatus,
                                        @Nonnull final String responseMessage) {
        return ErrorMessage.builder().status(httpStatus.value()).description(responseMessage).build();
    }
}