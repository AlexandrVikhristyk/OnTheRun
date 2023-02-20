package com.gachigang.ontherun.common.validator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

/**
 * The @ValidPassword annotation is used to verify that a given string value is a valid password.
 * The annotation accepts a `message` attribute, which specifies the error
 * message to be shown if the password is not valid. It also supports
 * the standard `groups` and `payload` attributes for use in validation
 * groups and payloads.
 * The annotation is used by the PasswordMatcherValidator class to perform the actual validation.
 */

@Target({ElementType.METHOD, ElementType.FIELD, ElementType.ANNOTATION_TYPE,
        ElementType.CONSTRUCTOR, ElementType.PARAMETER, ElementType.TYPE_USE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(validatedBy = PasswordMatchesValidator.class)
public @interface ValidPassword {

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
