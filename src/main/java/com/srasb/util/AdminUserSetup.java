package com.srasb.util;

import com.srasb.model.entity.UserEntity;
import com.srasb.model.entity.UserRole;
import com.srasb.service.userservice.UserRepositoryService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;


@Component
public class AdminUserSetup {

    @Value("${admin.password}")
    private String adminPassword;

    @Bean
    public AdminUserSetup createAdminUser(PasswordEncoder passwordEncoder, UserRepositoryService userRepositoryService) {

        if (!userRepositoryService.findByName("Admin")) {
            UserEntity newUser = new UserEntity();
            newUser.setId(0);
            newUser.setUsername("Admin");
            newUser.setPassword(passwordEncoder.encode(adminPassword));
            newUser.setUserRole(UserRole.ROLE_ADMIN);
            userRepositoryService.add(newUser);
        }

        return new AdminUserSetup();
    }
}
