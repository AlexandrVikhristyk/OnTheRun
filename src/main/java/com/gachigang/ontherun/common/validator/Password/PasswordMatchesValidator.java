package com.gachigang.ontherun.common.validator.Password;

import com.gachigang.ontherun.payload.user.request.RegisterRequest;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class PasswordMatchesValidator implements ConstraintValidator<PasswordMatches, RegisterRequest> {

    @Override
    public boolean isValid(RegisterRequest registerRequest, ConstraintValidatorContext constraintValidatorContext) {
        return registerRequest.getPassword().equals(registerRequest.getConfirmPassword());
    }
}