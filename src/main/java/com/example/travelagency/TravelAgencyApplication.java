package com.example.travelagency;

import com.example.travelagency.entity.AppUser;
import com.example.travelagency.entity.Role;
import com.example.travelagency.service.RoleService;
import com.example.travelagency.service.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.HashSet;

@SpringBootApplication
public class TravelAgencyApplication {

    public static void main(String[] args) {
        SpringApplication.run(TravelAgencyApplication.class, args);
    }

    @Bean
    CommandLineRunner run(UserService userService, RoleService roleService) {
        return args -> {
            roleService.createRole(new Role(null, "ADMIN"));
            roleService.createRole(new Role(null, "USER"));

            userService.createUser(new AppUser(null, "Pontus Redig", "pontus", "1234", new HashSet<>()));
            userService.createUser(new AppUser(null, "Kozue Yamada", "kozue", "1234", new HashSet<>()));
            userService.createUser(new AppUser(null, "Sofia Rodriguez", "sofia", "1234", new HashSet<>()));

            userService.addRoleToUser("pontus", "USER");
            userService.addRoleToUser("pontus", "ADMIN");
            userService.addRoleToUser("kozue", "USER");
            userService.addRoleToUser("sofia", "USER");
        };
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
