package com.example.travelagency;

import com.example.travelagency.dto.RoleDto;
import com.example.travelagency.dto.UserDto;
import com.example.travelagency.service.RoleService;
import com.example.travelagency.service.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.web.firewall.HttpFirewall;
import org.springframework.security.web.firewall.StrictHttpFirewall;

@SpringBootApplication
public class TravelAgencyApplication {

    public static void main(String[] args) {
        SpringApplication.run(TravelAgencyApplication.class, args);
    }

    @Bean
    CommandLineRunner run(RoleService roleService, UserService userService) {
        return args -> {
            roleService.createRole(new RoleDto("USER"));
            roleService.createRole(new RoleDto("ADMIN"));

            userService.createUser(new UserDto("Sofia Rodriguez", "sofia", "1234"));
            userService.addRoleToUser("sofia", "ADMIN");

            userService.createUser(new UserDto("Kozue Yamada", "kozue", "5555"));
            userService.addRoleToUser("kozue", "ADMIN");
        };
    }

//    @Bean
//    public HttpFirewall allowUrlSemicolonHttpFirewall() {
//        StrictHttpFirewall firewall = new StrictHttpFirewall();
//        firewall.setAllowSemicolon(true);
//        return firewall;
//    }
}
