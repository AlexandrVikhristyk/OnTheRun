package com.gachigang.ontherun.persistence.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.envers.Audited;
import org.hibernate.envers.NotAudited;

import java.util.Set;

/**
 * Business class represents a business in the db.
 */
@Audited
@Builder
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Business extends Audit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String country;
    private String city;

    @ManyToMany(mappedBy = "businesses")
    Set<User> owners;

    @NotAudited
    @OneToMany(mappedBy = "business")
    private Set<Department> departments;
}
