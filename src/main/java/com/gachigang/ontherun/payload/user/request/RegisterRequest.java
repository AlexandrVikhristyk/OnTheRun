package com.gachigang.ontherun.payload.user.request;

import com.gachigang.ontherun.common.validator.Email.ValidEmail;
import com.gachigang.ontherun.common.validator.Password.PasswordMatches;
import com.gachigang.ontherun.common.validator.Password.ValidPassword;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


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

    @ValidPassword
    private String password;
    private String confirmPassword;
    private Long roleId;
}
