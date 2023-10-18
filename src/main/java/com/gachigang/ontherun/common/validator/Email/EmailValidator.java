package com.gachigang.ontherun.common.validator.Email;

import com.gachigang.ontherun.common.ApplicationConstants;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.lang.NonNull;

import java.util.regex.Matcher;

/**
 * Class validate an email address using a regular expression.
 * The class implements the {@link ConstraintValidator} interface.
 */
public class EmailValidator implements ConstraintValidator<ValidEmail, String> {

    @Override
    public boolean isValid(@NonNull String email,
                           @NonNull ConstraintValidatorContext context) {
        return validateEmail(email);
    }

    private boolean validateEmail(@NonNull final String email) {
        final Matcher matcher = ApplicationConstants.Validation.EMAIL_PATTERN.matcher(email);
        return matcher.matches();
    }
}