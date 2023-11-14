package com.gachigang.ontherun.common.exception;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ApplicationException extends RuntimeException {
    private String errorMessageKey;
    private String logMessage;
}