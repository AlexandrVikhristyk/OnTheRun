package com.gachigang.ontherun.common.validator;

import com.gachigang.ontherun.common.ApplicationConstants;
import lombok.NonNull;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.regex.Matcher;

public class PasswordMatchesValidator implements ConstraintValidator<PasswordMatches, String> {

    @Override
    public boolean isValid(@NonNull String password,
                           @NonNull ConstraintValidatorContext constraintValidatorContext) {
        return validatePassword(password);
    }

    private boolean validatePassword(@NonNull final String password) {
        final Matcher matcher = ApplicationConstants.Validation.PASSWORD_PATTERN.matcher(password);
        return matcher.matches();
    }
}