package com.gachigang.ontherun.common.validator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

import static java.lang.annotation.RetentionPolicy.RUNTIME;


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
