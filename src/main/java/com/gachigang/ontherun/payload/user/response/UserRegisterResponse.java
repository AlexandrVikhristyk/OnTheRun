package com.gachigang.ontherun.payload.user.response;

import com.gachigang.ontherun.service.UserService;
import lombok.AllArgsConstructor;
import lombok.Builder;

import java.time.LocalDate;

@Builder
@AllArgsConstructor
public class UserRegisterResponse {
    private final UserService userService;
    private Long id;
    private String surname;
    private String lastname;
    private String login;
    private String email;
    private LocalDate dateOfBirth;
    private String country;
    private String city;
}
