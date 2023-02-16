package com.gachigang.ontherun.utils;

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
        Role role1 = new Role();
        Role role2 = new Role();
        Role role3= new Role();

        role1.setName("STAFF");
        role2.setName("MANAGER");
        role3.setName("USER");

        roleRepository.save(role1);
        roleRepository.save(role2);
        roleRepository.save(role3);
    }
}
