package com.gachigang.ontherun.controller;

import com.gachigang.ontherun.payload.user.request.LoginRequest;
import com.gachigang.ontherun.payload.user.request.RegisterRequest;
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
    public ResponseEntity<String> registerUser(@Valid @RequestBody RegisterRequest registerRequest) {
        authService.register(registerRequest);

        return ResponseEntity.ok("User registered");
    }
}
