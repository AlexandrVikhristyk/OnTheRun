package com.gachigang.ontherun.common.validator;

import com.gachigang.ontherun.payload.user.request.RegisterRequest;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class PasswordMatchesValidator implements ConstraintValidator<PasswordMatches, RegisterRequest> {

    @Override
    public boolean isValid(RegisterRequest registerRequest, ConstraintValidatorContext constraintValidatorContext) {
        return registerRequest.getPassword().equals(registerRequest.getConfirmPassword());
    }
}