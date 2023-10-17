package com.gachigang.ontherun.service;

import com.gachigang.ontherun.common.enums.UserRole;
import com.gachigang.ontherun.common.exception.AccountDisabledException;
import com.gachigang.ontherun.payload.user.request.LoginRequest;
import com.gachigang.ontherun.payload.user.request.RegisterRequest;
import com.gachigang.ontherun.payload.user.response.AuthenticationResponse;
import com.gachigang.ontherun.persistence.entity.Role;
import com.gachigang.ontherun.persistence.entity.User;
import com.gachigang.ontherun.persistence.repository.RoleRepository;
import com.gachigang.ontherun.persistence.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
@AllArgsConstructor
public class AuthService {

    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final UserService userService;
    private final JwtService jwtService;
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final RefreshTokenService refreshTokenService;

    public void authenticateUser(LoginRequest loginRequest) {
        User user = userService.getByEmail(loginRequest.getEmail());

        if (!user.isEnabled()) {
            throw new AccountDisabledException("Account has been disabled");
        }

        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                user.getEmail(),
                loginRequest.getPassword(),
                user.getAuthorities()
        ));

        SecurityContextHolder.getContext().setAuthentication(authentication);
    }

    public AuthenticationResponse register(RegisterRequest registerRequest) {
        Role role = roleRepository.findById(registerRequest.getRoleId())
                .orElse(roleRepository.findByName(UserRole.USER.getRole()));

        User user = User.builder()
                .email(registerRequest.getEmail())
                .password(passwordEncoder.encode(registerRequest.getPassword()))
                .roles(Collections.singleton(role))
                .build();

        User savedUser = userRepository.save(user);
        String jwtToken = jwtService.generateToken(user);
        String refreshToken = jwtService.generateRefreshToken(user);
        refreshTokenService.saveUserToken(savedUser, jwtToken);

        return AuthenticationResponse.builder()
                .accessToken(jwtToken)
                .refreshToken(refreshToken)
                .build();
    }
}