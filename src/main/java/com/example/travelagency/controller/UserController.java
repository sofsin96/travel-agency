package com.example.travelagency.controller;

import com.example.travelagency.entity.User;
import com.example.travelagency.exception.CustomNotFoundException;
import com.example.travelagency.service.UserService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.NOT_FOUND;

@RestController @RequestMapping("/api/v1/users") @RequiredArgsConstructor
public class UserController {

    private static final String DESTINATION_NAME = "created-user";

    private final UserService userService;
    private final JmsTemplate jmsTemplate;

    @PostMapping("/createuser")
    @ResponseStatus(CREATED)
    public User createUser(@Valid @RequestBody User user) {

        User createdUser = userService.createUser(user);

        ObjectMapper objMapper = new ObjectMapper();
        try {
            jmsTemplate.convertAndSend(DESTINATION_NAME, objMapper.writeValueAsString(createdUser));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return createdUser;
    }

    @GetMapping("")
    public ResponseEntity<List<User>> getUsers() {
        return ResponseEntity.ok().body(userService.getUsers());
    }

    @GetMapping("/{id}")
    public User getUserById(@PathVariable Long id) {
        return userService.getUserById(id)
                .orElseThrow(() -> new CustomNotFoundException("User ID", id));
    }

    @PostMapping("/addroletouser")
    public ResponseEntity<?> addRoleToUser(@RequestParam String username, @RequestParam("rolename") String roleName) {
        userService.addRoleToUser(username, roleName);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/deleterolefromuser")
    public ResponseEntity<?> deleteRoleFromUser(@RequestParam String username, @RequestParam("rolename") String roleName) {
        userService.deleteRoleFromUser(username, roleName);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
    }
}