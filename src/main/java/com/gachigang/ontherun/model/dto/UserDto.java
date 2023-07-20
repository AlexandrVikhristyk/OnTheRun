package com.gachigang.ontherun.model.dto;

import com.gachigang.ontherun.persistence.entity.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 *  DTO class to represent {@link User}.
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {
    private String surname;
    private String lastname;
    private String login;
    private String email;
    private LocalDateTime dateOfBirth;
    private String country;
    private String city;
    private String password;
}
