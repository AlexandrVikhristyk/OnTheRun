package com.gachigang.ontherun.common.validator;

import lombok.AllArgsConstructor;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.validation.ConstraintValidatorContext;

import static org.junit.Assert.assertEquals;

@ExtendWith(MockitoExtension.class)
public class PasswordValidatorTest {

    private final static String CORRECTPASSWORD = "Testpassword1";
    private final static String INCORRECTPASSWORD = "testpassword";

    @InjectMocks
    private PasswordMatchesValidator passwordMatchesValidator;
    @Mock
    private ConstraintValidatorContext context;

    @Test
    public void testIsValidPassword_Positive() {
        assertEquals(true, passwordMatchesValidator.isValid(CORRECTPASSWORD, context));
    }

    @Test
    public void testIsValidPassword_Negative() {
        assertEquals(false, passwordMatchesValidator.isValid(INCORRECTPASSWORD, context));
    }
}
