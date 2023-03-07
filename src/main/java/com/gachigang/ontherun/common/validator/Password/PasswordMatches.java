package com.gachigang.ontherun.common.validator.Password;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

/**
 * The @PasswordMatches annotation is used to verify that password is equal to confirmPassword in the DTO.
 * The annotation accepts a `message` attribute, which specifies the error
 * message to be shown if the password is not equals. It also supports
 * the standard `groups` and `payload` attributes for use in validation
 * groups and payloads.
 * The annotation is used by the PasswordMatcherValidator class to perform the actual validation.
 */
@Target({ElementType.TYPE, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(validatedBy = PasswordMatchesValidator.class)
public @interface PasswordMatches {

    /**
     * Error message.
     */
    String message() default "Passwords do not match";

    /**
     * Group attribute.
     */
    Class<?>[] groups() default {};

    /**
     * Payload attribute.
     */
    Class<? extends Payload>[] payload() default {};
}