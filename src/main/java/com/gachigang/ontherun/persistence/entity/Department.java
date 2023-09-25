package com.gachigang.ontherun.persistence.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.envers.Audited;
import org.hibernate.envers.NotAudited;

import java.util.Set;
@Audited
@Builder
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Department extends Audit {

    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    @Column(name = "id", updatable = false)
    private Long id;

    @NotAudited
    @OneToMany(mappedBy = "department")
    private Set<User> users;

    @ManyToOne
    @JoinColumn(name = "business_id")
    private Business business;
}
