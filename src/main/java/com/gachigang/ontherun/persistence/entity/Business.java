package com.gachigang.ontherun.persistence.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

/**
 * Business class represents a business in the db.
 */
@Builder
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Business {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String country;
    private String city;

    @ManyToMany(mappedBy = "businesses")
    Set<User> owners;

    @OneToMany(mappedBy = "business")
    private Set<Department> departments;
}
