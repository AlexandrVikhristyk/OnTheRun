package com.gachigang.ontherun.persistence.repository;

import com.gachigang.ontherun.persistence.entity.User;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

/**
 * Basic repository for the user.
 */
public interface UserRepository extends CrudRepository<User, Long> {
    Optional<User> findUserByEmail(String email);
    boolean existsByEmail(String email);
}
