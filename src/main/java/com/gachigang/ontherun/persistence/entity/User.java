package com.gachigang.ontherun.persistence.entity;

import com.gachigang.ontherun.common.validator.ValidEmail;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
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

    @Column(name = "surname")
    String surname;

    @Column(name = "lastname")
    String lastname;

    @NotBlank
    @NotEmpty
    String login;

    @ValidEmail
    String email;

    String dateOfBirth;

    String country;

    String city;
}
