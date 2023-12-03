package com.gachigang.ontherun.persistence.repository;

import com.gachigang.ontherun.persistence.entity.User;
import jakarta.annotation.Nonnull;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;
import java.util.Set;

/**
 * The {@code UserRepository} interface defines basic methods for interacting with
 * {@link User} entities.
 *
 * <p>This repository provides CRUD operations for managing users in the system.
 *
 * @see User
 */
public interface UserRepository extends CrudRepository<User, Long> {

    /**
     * Finds a user by their email address.
     *
     * @param email The email address of the user to be found.
     * @return An {@link Optional} containing the found user or an empty optional if not found.
     */
    Optional<User> findUserByEmail(@Nonnull String email);

    /**
     * Find a user by the id.
     *
     * @param id The id of the user. Must not be {@code null}.
     * @return An {@code Optional} containing the user with the specified identifier,
     * or an empty {@code Optional} if no user is found.
     */
    Optional<User> findUsersById(@Nonnull Long id);

    /**
     * Finds all users with IDs in the specified set.
     *
     * @param idSet The set of user IDs.
     * @return The set of users with IDs in the specified set.
     */
    Set<User> findAllByIdIn(@Nonnull Set<Long> idSet);

    /**
     * Checks if a user with the given email address exists.
     *
     * @param email The email address to check for existence.
     * @return {@code true} if a user with the given email address exists, {@code false} otherwise.
     */
    boolean existsByEmail(@Nonnull String email);
}
