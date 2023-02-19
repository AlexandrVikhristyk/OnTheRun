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
    public void run(String... args) throws Exception {

        for(UserRole role : UserRole.values()) {
            Role staff = new Role();
            staff.setName(role.getRole());
            roleRepository.save(staff);
        }

        for(UserRole role : UserRole.values()) {
            Role manager = new Role();
            manager.setName(role.getRole());
            roleRepository.save(manager);
        }

        for(UserRole role : UserRole.values()) {
            Role staff = new Role();
            staff.setName(role.getRole());
            roleRepository.save(staff);
        }
    }
}
