package com.gachigang.ontherun.persistence.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

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

    @ManyToMany(mappedBy = "businesses", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<User> owners;

    @OneToMany(mappedBy = "business", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<Department> departments;
}