package com.gachigang.ontherun.controller;

import com.gachigang.ontherun.payload.user.request.LoginRequest;
import com.gachigang.ontherun.payload.user.request.RegisterRequest;
import com.gachigang.ontherun.payload.user.response.UserRegisterResponse;
import com.gachigang.ontherun.persistence.entity.User;
import com.gachigang.ontherun.service.AuthService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@CrossOrigin
@RestController
@RequestMapping("/auth")
@AllArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<Object> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {
        authService.authenticateUser(loginRequest);
        return ResponseEntity.ok("User successfully logged in");
    }

    @PostMapping("/register")
    public ResponseEntity<UserRegisterResponse> registerUser(@Valid @RequestBody RegisterRequest registerRequest) {
        User user = authService.register(registerRequest);
        UserRegisterResponse registerResponse = UserRegisterResponse.builder()
                .id(user.getId())
                .surname(user.getSurname())
                .lastname(user.getLastname())
                .login(user.getLogin())
                .email(user.getEmail())
                .dateOfBirth(user.getDateOfBirth())
                .country(user.getCountry())
                .city(user.getCity())
                .build();

        return ResponseEntity.ok(registerResponse);
    }
}
