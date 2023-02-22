package com.gachigang.ontherun.payload.user.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LoginRequest {

    @NotEmpty(message = "Username cannot be empty")
    private String email;

    @NotEmpty(message = "Password cannot be empty")
    private String password;
}
