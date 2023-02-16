package com.gachigang.ontherun.payload.user.request;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
public class LoginRequest {

    @NotEmpty(message = "Username cannot be empty")
    private String email;

    @NotEmpty(message = "Password cannot be empty")
    private String password;
}
