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
        Role staff = new Role();
        staff.setName(UserRole.STAFF.getRole());
        roleRepository.save(staff);

        Role manager = new Role();
        manager.setName(UserRole.MANAGER.getRole());
        roleRepository.save(manager);

        Role user = new Role();
        user.setName(UserRole.USER.getRole());
        roleRepository.save(user);
    }
}
