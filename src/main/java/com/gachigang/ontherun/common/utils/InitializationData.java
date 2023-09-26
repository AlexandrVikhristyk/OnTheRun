package com.gachigang.ontherun.common.utils;

import com.gachigang.ontherun.common.enums.CategoryName;
import com.gachigang.ontherun.common.enums.UserRole;
import com.gachigang.ontherun.persistence.entity.Business;
import com.gachigang.ontherun.persistence.entity.Category;
import com.gachigang.ontherun.persistence.entity.Department;
import com.gachigang.ontherun.persistence.entity.Role;
import com.gachigang.ontherun.persistence.repository.BusinessRepository;
import com.gachigang.ontherun.persistence.repository.CategoryRepository;
import com.gachigang.ontherun.persistence.repository.DepartmentRepository;
import com.gachigang.ontherun.persistence.repository.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/**
 * Class for initializing data in the DB
 */
@Component
@RequiredArgsConstructor
public class InitializationData implements CommandLineRunner {

    @Value("${spring.jpa.hibernate.ddl-auto}")
    private String ddlAuto;
    private final RoleRepository roleRepository;
    private final BusinessRepository businessRepository;
    private final DepartmentRepository departmentRepository;
    private final CategoryRepository categoryRepository;

    @Override
    public void run(String... args) {
        if (ddlAuto.startsWith("create")) {
            for (UserRole userRole : UserRole.values()) {
                Role role = new Role();
                role.setName(userRole.getRole());
                roleRepository.save(role);
            }
        }


        for (int i = 0; i < 3; i++) {
            Business business  = Business.builder()
                    .city("Kiev" + i)
                    .country("Ukraine")
                    .name("Test"+i)
                    .build();
            businessRepository.save(business);

            Department department = Department.builder()
                    .business(business)
                    .build();
            departmentRepository.save(department);
        }

        for (CategoryName categoryName : CategoryName.values()) {
            Category category = Category.builder()
                    .name(categoryName)
                    .description("Description for " + categoryName.getDisplayName())
                    .build();

            categoryRepository.save(category);
        }
    }
}
