package com.gachigang.ontherun.persistence.entity;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "department")
public class Department {
    @Id
    @SequenceGenerator(
            name = "department_sequence",
            sequenceName = "department_sequence",
            allocationSize = 1
    )

    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "department_sequence"
    )

    @Column(
            name = "id",
            updatable = false
    )

    private Long id;

    public Department() {
    }

    public Department(Set<User> users, Business business) {
        this.users = users;
        this.business = business;
    }

    @OneToMany(mappedBy = "department")
    private Set<User> users;

    @ManyToOne
    @JoinColumn(name = "business_id", nullable = false)
    private Business business;

    @Override
    public String toString() {
        return "Department{" +
                "id=" + id +
                ", users=" + users +
                ", business=" + business +
                '}';
    }
}
