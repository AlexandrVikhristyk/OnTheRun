package com.gachigang.ontherun.service;

import com.gachigang.ontherun.payload.user.request.LoginRequest;
import com.gachigang.ontherun.payload.user.request.RegisterRequest;
import com.gachigang.ontherun.persistence.entity.User;
import lombok.AllArgsConstructor;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@AllArgsConstructor
public class AuthServiceTest {

    private final String EMAIL = "test@gmail.com";
    private final String PASSWORD = "password";

    @MockBean
    private final PasswordEncoder encoder;
    @MockBean
    private final AuthenticationManager authenticationManager;
    @Mock
    private final UserService userService;

    @InjectMocks
    private final AuthService authService;


    @Test
    public void testAuthenticateUser() {
        LoginRequest loginRequest =LoginRequest.builder()
                .email(EMAIL)
                .password(PASSWORD)
                .build();

        User user = User.builder()
                .email(EMAIL)
                .password(PASSWORD)
                .build();

        when(userService.getByEmail(EMAIL)).thenReturn(user);

        authService.authenticateUser(loginRequest);

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        assertNotNull(authentication);
        assertEquals(EMAIL,authentication.getName());
    }

    @Test
    public void testUserRegister() {
//        RegisterRequest registerRequest = RegisterRequest.builder()
//                .email(EMAIL)
//                .password(PASSWORD)
//                .confirmPassword(PASSWORD)
//                .build();
//
//        authService.register(registerRequest);
//
//        verify(encoder).encode(any());
//        verify(userService).save(any());
    }
}
