package com.gachigang.ontherun.common.validator.Email;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * The @UniqueEmail annotation is used to verify that a given email has already existed in the Database.
 * The annotation accepts a `message` attribute, which specifies the error
 * message to be shown if the email address exists in the Database. It also supports
 * the standard `groups` and `payload` attributes for use in validation
 * groups and payloads.
 * The annotation is used by the UniqueEmailValidator class to perform the actual validation.
 */
@Target({ElementType.METHOD, ElementType.FIELD, ElementType.ANNOTATION_TYPE,
        ElementType.CONSTRUCTOR, ElementType.PARAMETER, ElementType.TYPE_USE})
@Retention(RUNTIME)
@Documented
@Constraint(validatedBy = UniqueEmailValidator.class)
public @interface UniqueEmail {

    /**
     * Error message.
     */
    String message() default "There is already user with this email!";

    /**
     * Group attribute.
     */
    Class<?>[] groups() default {};

    /**
     * Payload attribute.
     */
    Class<? extends Payload>[] payload() default {};
}
