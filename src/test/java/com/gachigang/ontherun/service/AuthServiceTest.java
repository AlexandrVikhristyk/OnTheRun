package com.gachigang.ontherun.service;

import com.gachigang.ontherun.common.enums.UserRole;
import com.gachigang.ontherun.payload.user.request.LoginRequest;
import com.gachigang.ontherun.payload.user.request.RegisterRequest;
import com.gachigang.ontherun.payload.user.response.AuthenticationResponse;
import com.gachigang.ontherun.persistence.entity.Role;
import com.gachigang.ontherun.persistence.entity.Token;
import com.gachigang.ontherun.persistence.entity.User;
import com.gachigang.ontherun.persistence.repository.RoleRepository;
import com.gachigang.ontherun.persistence.repository.TokenRepository;
import com.gachigang.ontherun.persistence.repository.UserRepository;
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
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class AuthServiceTest {

    private static final String EMAIL = "test@gmail.com";
    private static final String PASSWORD = "Password123";
    private static final Role ROLE = Role.builder()
            .name(UserRole.USER.getRole())
            .permissions(Collections.EMPTY_LIST)
            .build();

    @Mock
    private PasswordEncoder encoder;
    @Mock
    private AuthenticationManager authenticationManager;
    @Mock
    private UserService userService;
    @Mock
    private JwtService jwtService;
    @Mock
    private TokenRepository tokenRepository;
    @Mock
    private UserRepository userRepository;
    @Mock
    private RoleRepository roleRepository;

    @InjectMocks
    private AuthService authService;



    @Test
    public void testAuthenticateUser_Positive() {
        LoginRequest loginRequest = LoginRequest.builder()
                .email(EMAIL)
                .password(PASSWORD)
                .build();

        User user = User.builder()
                .email(EMAIL)
                .password(PASSWORD)
                .roles(Collections.singleton(ROLE))
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
        LoginRequest loginRequest = LoginRequest.builder()
                .email(EMAIL)
                .password(PASSWORD + "_wrong")
                .build();

        User user = User.builder()
                .email(EMAIL)
                .password(PASSWORD)
                .roles(Collections.singleton(ROLE))
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
                .roleId(1L)
                .build();

        when(roleRepository.findById(1L)).thenReturn(Optional.of(ROLE));

        AuthenticationResponse expectedResponse = AuthenticationResponse.builder()
                .accessToken("sampleAccessToken")
                .refreshToken("sampleRefreshToken")
                .build();

        User savedUser = User.builder()
                .email(EMAIL)
                .password(PASSWORD)
                .roles(Collections.singleton(ROLE))
                .build();

        when(userRepository.save(any(User.class))).thenReturn(savedUser);
        when(jwtService.generateToken(any(User.class))).thenReturn("sampleAccessToken");
        when(jwtService.generateRefreshToken(any(User.class))).thenReturn("sampleRefreshToken");

        AuthenticationResponse response = authService.register(registerRequest);

        verify(userRepository, times(1)).save(any(User.class));
        verify(jwtService, times(1)).generateToken(any(User.class));
        verify(jwtService, times(1)).generateRefreshToken(any(User.class));
        verify(tokenRepository, times(1)).save(any(Token.class));

        assertEquals(expectedResponse, response);
    }


    @Test
    public void testRegister_Negative() {
        RegisterRequest registerRequest = RegisterRequest.builder()
                .email(EMAIL)
                .password(PASSWORD)
                .roleId(1L)
                .build();

        when(roleRepository.findById(1L)).thenReturn(Optional.of(ROLE));

        when(userRepository.save(any(User.class))).thenThrow(new RuntimeException("Failed to save user"));

        assertThrows(RuntimeException.class, () -> {
            authService.register(registerRequest);
        });

        verify(userRepository, times(1)).save(any(User.class));
        verify(jwtService, never()).generateToken(any(User.class));
        verify(jwtService, never()).generateRefreshToken(any(User.class));
        verify(tokenRepository, never()).save(any(Token.class));
    }
}
