package com.gachigang.ontherun.service;

import com.gachigang.ontherun.common.exception.AccountDisabledException;
import com.gachigang.ontherun.payload.user.request.LoginRequest;
import com.gachigang.ontherun.payload.user.request.RegisterRequest;
import com.gachigang.ontherun.persistence.entity.User;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AuthService {

    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final UserService userService;

    public void authenticateUser(LoginRequest loginRequest) {
        User user = userService.getByEmail(loginRequest.getEmail());

        if (user.isEnabled()) {
            throw new AccountDisabledException("Account has been disabled");
        }

        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                user.getEmail(),
                loginRequest.getPassword(),
                user.getAuthorities()
        ));

        SecurityContextHolder.getContext().setAuthentication(authentication);
    }

    public User register(RegisterRequest registerRequest) {
        User user = User.builder()
                .email(registerRequest.getEmail())
                .password(passwordEncoder.encode(registerRequest.getPassword()))
                .build();

        return userService.save(user);
    }
}
