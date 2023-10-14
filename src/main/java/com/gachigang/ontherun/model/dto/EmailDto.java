package com.gachigang.ontherun.model.dto;

import jakarta.annotation.Nullable;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Data
@Builder
public class EmailDto {

    @Email
    @NotNull
    private String to;

    @Nullable
    private String subject;

    @Nullable
    private String content;
}
