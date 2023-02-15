package com.gachigang.ontherun.common.validator;


import jakarta.validation.ConstraintValidatorContext;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.Assert.assertEquals;

@ExtendWith(MockitoExtension.class)
public class EmailValidatorTest {

    private final static String correctEmail = "test@gmail.com";
    private final static String incorrectEmail = "testgmailcom";
    @InjectMocks
    private EmailValidator emailValidator;
    @Mock
    private ConstraintValidatorContext context;

    @Test
    public void testIsValidEmailShouldReturnTrue() {
        assertEquals(true, emailValidator.isValid(correctEmail, context));
    }

    @Test
    public void testIsValidEmailShouldReturnFalse() {
        assertEquals(false, emailValidator.isValid(incorrectEmail, context));
    }
}

