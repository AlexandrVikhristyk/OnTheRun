package com.gachigang.ontherun.common.validator;

import com.gachigang.ontherun.service.UserDetailsLoaderService;
import lombok.AllArgsConstructor;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

@AllArgsConstructor
public class UniqueEmailValidator implements ConstraintValidator<UniqueEmail, String> {

    private final UserDetailsLoaderService userDetailsLoaderService;
    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return value != null && !userDetailsLoaderService.isEmailAlreadyInUse(value);
    }
}
