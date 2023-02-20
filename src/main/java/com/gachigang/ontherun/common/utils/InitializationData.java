package com.gachigang.ontherun.common.utils;

import com.gachigang.ontherun.common.enums.UserRole;
import com.gachigang.ontherun.persistence.entity.Role;
import com.gachigang.ontherun.persistence.repository.RoleRepository;
import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@AllArgsConstructor
@Component
public class InitializationData implements CommandLineRunner {

    private final RoleRepository roleRepository;

    @Override
    public void run(String... args) {
        for(UserRole userRole : UserRole.values()) {
            Role role = new Role();
            role.setName(userRole.getRole());
            roleRepository.save(role);
        }
    }
}
