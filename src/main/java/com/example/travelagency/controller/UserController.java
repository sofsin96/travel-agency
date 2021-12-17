package com.example.travelagency.controller;

import com.example.travelagency.entity.AppUser;
import com.example.travelagency.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.NOT_FOUND;

@RestController @RequestMapping("/api/v1/users") @RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("")
    @ResponseStatus(CREATED)
    public AppUser createUser(@RequestBody AppUser user) {
        return userService.createUser(user);
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
    public ResponseEntity<AppUser> addRoleToUser(@RequestParam String username, @RequestParam String roleName) {
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
}