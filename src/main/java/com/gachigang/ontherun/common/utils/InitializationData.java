package com.gachigang.ontherun.common.utils;

import com.gachigang.ontherun.common.enums.UserRole;
import com.gachigang.ontherun.persistence.entity.Role;
import com.gachigang.ontherun.persistence.repository.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;


@Component
@RequiredArgsConstructor
public class InitializationData implements CommandLineRunner {

    @Value("${spring.jpa.hibernate.ddl-auto}")
    private String ddlAuto;
    private final RoleRepository roleRepository;

    @Override
    public void run(String... args) {
        if (ddlAuto.startsWith("create")) {
            for (UserRole userRole : UserRole.values()) {
                Role role = new Role();
                role.setName(userRole.getRole());
                roleRepository.save(role);
            }
        }
    }
}
