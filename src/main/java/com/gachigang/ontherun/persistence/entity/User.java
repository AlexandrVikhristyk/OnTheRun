package com.gachigang.ontherun.persistence.entity;

import com.gachigang.ontherun.common.validator.Email.ValidEmail;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.envers.Audited;
import org.hibernate.envers.NotAudited;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;

/**
 * User class represents a user in the db.
 */
@Audited
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString(exclude = {"tokens", "department", "businesses", "roles"})
@EqualsAndHashCode(exclude = {"tokens", "department", "businesses", "roles"}, callSuper = false)
@Data
@Table(name = "userInfo")
public class User extends Audit implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String surname;
    private String lastname;
    private String login;

    @ValidEmail
    private String email;

    private LocalDateTime dateOfBirth;
    private String country;
    private String city;
    private String password;

    @NotAudited
    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    private List<Token> tokens;

    @NotAudited
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "department_id")
    private Department department;

    @Column(name = "department_id", insertable = false, updatable = false)
    private Long departmentId;

    @NotAudited
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "users_business",
            joinColumns = @JoinColumn(
                    name = "user_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(
                    name = "business_id", referencedColumnName = "id"))
    private Set<Business> businesses;

    @NotAudited
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "users_role",
            joinColumns = @JoinColumn(
                    name = "user_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(
                    name = "role_id", referencedColumnName = "id"))
    private Set<Role> roles;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> authorities
                = new ArrayList<>();
        for (Role role : roles) {
            authorities.add(new SimpleGrantedAuthority(role.getName()));
            role.getPermissions().stream()
                    .map(p -> new SimpleGrantedAuthority(p.getName()))
                    .forEach(authorities::add);
        }

        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}