package com.gachigang.ontherun.payload.user.response;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Builder
@Data
public class UserRegisterResponse {

    private Long id;
    private String surname;
    private String lastname;
    private String login;
    private String email;
    private LocalDateTime dateOfBirth;
    private String country;
    private String city;
}
