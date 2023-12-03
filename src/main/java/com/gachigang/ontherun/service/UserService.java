package com.gachigang.ontherun.service;

import com.gachigang.ontherun.persistence.entity.User;
import com.gachigang.ontherun.persistence.repository.UserRepository;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    @Transactional(readOnly = true)
    public User getByEmail(@NonNull final String email) {
        return userRepository.findUserByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("Username not found with email " + email));
    }

    @Transactional(readOnly = true)
    public User getUserById(@NonNull final Long id) {
        return userRepository.findUsersById(id)
                .orElseThrow(() -> new UsernameNotFoundException("Username not found with id " + id));
    }

    @Transactional
    public User save(User user) {
        return userRepository.save(user);
    }
}
