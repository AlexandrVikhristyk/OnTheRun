package com.gachigang.ontherun.common.validator.Email;

import com.gachigang.ontherun.persistence.repository.UserRepository;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.AllArgsConstructor;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

/**
 * Class validate if the email address exists in the database.
 * The class implements the {@link ConstraintValidator} interface.
 */
@AllArgsConstructor
@Component
public class UniqueEmailValidator implements ConstraintValidator<UniqueEmail, String> {

    private final UserRepository userRepository;

    @Override
    public boolean isValid(@NonNull final String email, ConstraintValidatorContext context) {
        return !userRepository.existsByEmail(email);
    }
}