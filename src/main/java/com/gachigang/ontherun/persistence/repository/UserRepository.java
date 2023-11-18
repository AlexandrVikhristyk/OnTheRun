package com.gachigang.ontherun.persistence.repository;

import com.gachigang.ontherun.persistence.entity.User;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;
import java.util.Set;

/**
 * Basic repository for the user.
 */
public interface UserRepository extends CrudRepository<User, Long> {
    Optional<User> findUserByEmail(String email);
    Set<User> findAllByIdIn(Set<Long> idSet);
    Boolean existsByEmail(String email);
}
