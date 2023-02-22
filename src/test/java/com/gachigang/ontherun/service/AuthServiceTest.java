package com.gachigang.ontherun.service;

import com.gachigang.ontherun.common.enums.UserRole;
import com.gachigang.ontherun.payload.user.request.LoginRequest;
import com.gachigang.ontherun.payload.user.request.RegisterRequest;
import com.gachigang.ontherun.persistence.entity.Role;
import com.gachigang.ontherun.persistence.entity.User;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class AuthServiceTest {

    private final String EMAIL = "test@gmail.com";
    private final String PASSWORD = "password";

    @Mock
    private PasswordEncoder encoder;
    @Mock
    private AuthenticationManager authenticationManager;
    @Mock
    private UserService userService;

    @InjectMocks
    private AuthService authService;

    @Test
    public void testAuthenticateUser_Positive() {
        Role role = Role.builder()
                .name(UserRole.USER.getRole())
                .permissions(Collections.EMPTY_LIST)
                .build();

        LoginRequest loginRequest = LoginRequest.builder()
                .email(EMAIL)
                .password(PASSWORD)
                .build();

        User user = User.builder()
                .email(EMAIL)
                .password(PASSWORD)
                .roles(Collections.singleton(role))
                .build();

        when(userService.getByEmail(EMAIL)).thenReturn(user);

        authService.authenticateUser(loginRequest);

        verify(authenticationManager, times(1)).authenticate(
                new UsernamePasswordAuthenticationToken(
                        user.getEmail(),
                        loginRequest.getPassword(),
                        user.getAuthorities()));
    }

    @Test
    public void testAuthenticateUser_Negative() {
        Role role = Role.builder()
                .name(UserRole.USER.getRole())
                .permissions(Collections.EMPTY_LIST)
                .build();

        LoginRequest loginRequest = LoginRequest.builder()
                .email(EMAIL)
                .password(PASSWORD + "_wrong")
                .build();

        User user = User.builder()
                .email(EMAIL)
                .password(PASSWORD)
                .roles(Collections.singleton(role))
                .build();

        when(userService.getByEmail(EMAIL)).thenReturn(user);
        when(authenticationManager.authenticate(any(UsernamePasswordAuthenticationToken.class)))
                .thenThrow(new BadCredentialsException("Invalid email or password"));

        assertThrows(BadCredentialsException.class, () -> authService.authenticateUser(loginRequest));
    }

    @Test
    public void testUserRegister_Positive() {
        RegisterRequest registerRequest = RegisterRequest.builder()
                .email(EMAIL)
                .password(PASSWORD)
                .confirmPassword(PASSWORD)
                .build();

        authService.register(registerRequest);

        verify(encoder).encode(any());
        verify(userService).save(any());
    }

    @Test
    public void testUserRegister_Negative() {
        RegisterRequest registerRequest = RegisterRequest.builder()
                .email(EMAIL)
                .password(PASSWORD)
                .confirmPassword(PASSWORD)
                .build();

        doThrow(new RuntimeException("Database connection failed")).when(userService).save(any());
        assertThrows(RuntimeException.class, () -> authService.register(registerRequest));

        verify(encoder).encode(any());
        verify(userService).save(any());
    }
}
