package com.gachigang.ontherun.common.validator.Password;

import com.gachigang.ontherun.common.ApplicationConstants;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.NonNull;

import java.util.regex.Matcher;

/**
 * Class validate a password using a regular expression.
 * The class implements the {@link ConstraintValidator} interface.
 */
public class PasswordValidator implements ConstraintValidator<ValidPassword, String> {

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
