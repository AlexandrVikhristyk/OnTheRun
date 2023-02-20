package com.gachigang.ontherun.common.validator;

import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.validation.ConstraintValidatorContext;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(MockitoExtension.class)
public class PasswordValidatorTest {

    @Mock
    private ConstraintValidatorContext context;

    @InjectMocks
    private PasswordValidator passwordValidator;

    @ParameterizedTest(name = "#{index} - Run test with password = {0}")
    @MethodSource("validPasswordProvider")
    void test_password_regex_valid(String password) {
        assertTrue(passwordValidator.isValid(password, context));
    }

    @ParameterizedTest(name = "#{index} - Run test with password = {0}")
    @MethodSource("invalidPasswordProvider")
    void test_password_regex_invalid(String password) {
        assertFalse(passwordValidator.isValid(password, context));
    }

    static Stream<String> validPasswordProvider() {
        return Stream.of("AAAbbbccc@123", "Hello world$123", "A!@#&()–a1",               // test punctuation part 1
                "A[{}]:;',?/*a1",           // test punctuation part 2
                "A~$^+=<>a1",               // test symbols
                "0123456789$abcdefgAB",     // test 20 chars
                "123Aa$Aa"                  // test 8 chars
        );
    }

    // At least
    // one lowercase character,
    // one uppercase character,
    // one digit,
    // one special character
    // and length between 8 and 20.
    static Stream<String> invalidPasswordProvider() {
        return Stream.of("12345678",                 // invalid, only digit
                "abcdefgh",                 // invalid, only lowercase
                "ABCDEFGH",                 // invalid, only uppercase
                "ABC123$$$",                // invalid, at least one lowercase
                "ABC$$$$$$",                // invalid, at least one digit
                "________",                 // invalid
                "--------",                 // invalid
                " ",                        // empty
                "");
    }
}

