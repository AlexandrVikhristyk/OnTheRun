package com.gachigang.ontherun.common.validator.Email;

import com.gachigang.ontherun.persistence.repository.UserRepository;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import org.springframework.stereotype.Component;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

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
