package com.example.travelagency.controller;

import com.example.travelagency.entity.AppUser;
import com.example.travelagency.entity.Role;
import com.example.travelagency.service.UserService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.NOT_FOUND;

@RestController @RequestMapping("/api/v1/users") @RequiredArgsConstructor
public class UserController {

    private static final String DESTINATION_NAME = "message-queue";

    private final UserService userService;

    private final JmsTemplate jmsTemplate;

    @PostMapping("/createuser")
    @ResponseStatus(CREATED)
    public AppUser createUser(@RequestBody AppUser user) {
        AppUser createdUser = userService.createUser(user);

        ObjectMapper objMapper = new ObjectMapper();
        try {
            jmsTemplate.convertAndSend(DESTINATION_NAME, objMapper.writeValueAsString(createdUser));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return createdUser;
    }

    @GetMapping("")
    public ResponseEntity<List<AppUser>> getUsers() {
        return ResponseEntity.ok().body(userService.getUsers());
    }

    @GetMapping("/{id}")
    public AppUser getUserById(@PathVariable Long id) {
        return userService.getUserById(id)
                .orElseThrow(() -> new ResponseStatusException(NOT_FOUND,
                        "User with id " + id + " not found."));
    }

    @PostMapping("/addroletouser")
    public ResponseEntity<AppUser> addRoleToUser(@RequestParam String username, @RequestParam("rolename") String roleName) {
        userService.addRoleToUser(username, roleName);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/deleterolefromuser")
    public ResponseEntity<AppUser> deleteRoleFromUser(@RequestParam String username, @RequestParam String roleName) {
        userService.deleteRoleFromUser(username, roleName);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
    }

    @PostMapping("/createrole")
    @ResponseStatus(CREATED)
    public Role createRole(@RequestBody Role role) {
        return userService.createRole(role);
    }
}