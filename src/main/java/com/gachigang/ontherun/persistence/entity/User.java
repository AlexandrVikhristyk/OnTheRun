package com.gachigang.ontherun.persistence.entity;

import com.gachigang.ontherun.common.validator.ValidEmail;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import lombok.experimental.FieldDefaults;

/**
 * User class represents a user in the db.
 */

@Entity
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    String surname;
    String lastname;

    @NotBlank
    String login;

    @ValidEmail
    String email;

    @NotBlank
    String password;

    String dateOfBirth;
    String country;
    String city;
}
