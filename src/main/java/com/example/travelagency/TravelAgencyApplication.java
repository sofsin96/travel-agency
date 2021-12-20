package com.example.travelagency;

import com.example.travelagency.entity.Booking;
import com.example.travelagency.entity.Customer;
import com.example.travelagency.entity.User;
import com.example.travelagency.entity.Role;
import com.example.travelagency.service.BookingService;
import com.example.travelagency.service.CustomerService;
import com.example.travelagency.service.RoleService;
import com.example.travelagency.service.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.HashSet;

@EnableJms
@SpringBootApplication
public class TravelAgencyApplication {

    public static void main(String[] args) {
        SpringApplication.run(TravelAgencyApplication.class, args);
    }

    @Bean
    CommandLineRunner run(UserService userService, RoleService roleService, CustomerService customerService, BookingService bookingService) {
        return args -> {
            roleService.createRole(new Role("ADMIN"));
            roleService.createRole(new Role("USER"));

            userService.createUser(new User("Pontus Redig", "pontus", "1234", new HashSet<>()));
            userService.createUser(new User("Kozue Yamada", "kozue", "1234", new HashSet<>()));
            userService.createUser(new User("Sofia Rodriguez", "sofia", "1234", new HashSet<>()));

            userService.addRoleToUser("pontus", "USER");
            userService.addRoleToUser("pontus", "ADMIN");
            userService.addRoleToUser("kozue", "USER");
            userService.addRoleToUser("sofia", "USER");

            bookingService.createBooking(new Booking(1L, 100.00, null, null, new HashSet<>()));
            customerService.createCustomer(new Customer(1L, "Sofia", "Rodriguez", "test@test.com", null, new HashSet<>()));

            customerService.addItineraryToCustomer(1L, 1L);
        };
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
