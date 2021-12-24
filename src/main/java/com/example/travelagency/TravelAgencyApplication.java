package com.example.travelagency;

import com.example.travelagency.entity.Role;
import com.example.travelagency.entity.User;
import com.example.travelagency.service.RoleService;
import com.example.travelagency.service.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@EnableJms
@SpringBootApplication
public class TravelAgencyApplication {

    public static void main(String[] args) {
        SpringApplication.run(TravelAgencyApplication.class, args);
    }

    @Bean
    CommandLineRunner run(RoleService roleService, UserService userService) {
        return args -> {
            roleService.createRole(new Role("USER"));
            roleService.createRole(new Role("ADMIN"));

            userService.createUser(new User("Sofia Rodriguez", "sofia", "1234"));
            userService.addRoleToUser("sofia", "ADMIN");
        };
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
