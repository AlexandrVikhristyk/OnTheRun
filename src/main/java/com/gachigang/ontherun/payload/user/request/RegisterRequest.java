package com.gachigang.ontherun.payload.user.request;

import com.gachigang.ontherun.common.validator.Password.PasswordMatches;
import com.gachigang.ontherun.common.validator.Email.ValidEmail;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Data
@PasswordMatches
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RegisterRequest {
    @Email(message = "It should have email format")
    @NotBlank(message = "User email is required")
    @ValidEmail
    private String email;

    @NotEmpty(message = "Password is required")
    @Size(min = 6)
    private String password;
    private String confirmPassword;
}
