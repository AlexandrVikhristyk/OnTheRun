package com.gachigang.ontherun.persistence.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.envers.NotAudited;

import java.util.Set;

@Builder
@Entity
@Data
@ToString(exclude = "business")
@EqualsAndHashCode(exclude = "business")
@AllArgsConstructor
@NoArgsConstructor
public class Department {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false)
    private Long id;

    @NotAudited
    @OneToMany(mappedBy = "department", fetch = FetchType.LAZY)
    private Set<User> users;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "business_id")
    private Business business;

    @Column(name = "business_id", insertable = false, updatable = false)
    private Long businessId;
}