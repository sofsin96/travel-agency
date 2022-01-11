package com.example.travelagency.controller;

import com.example.travelagency.dto.UserDto;
import com.example.travelagency.dto.UserFullName;
import com.example.travelagency.service.UserService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
//import org.springframework.jms.core.JmsTemplate;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

import static org.springframework.http.HttpStatus.CREATED;

@RestController @RequestMapping("/api/v1/users") @RequiredArgsConstructor
public class UserController {

    private static final String DESTINATION_NAME = "created-user";

    private final UserService userService;
//    private final JmsTemplate jmsTemplate;

    @PostMapping("/create")
    @ResponseStatus(CREATED)
    public UserDto createUser(@Valid @RequestBody UserDto userDto) {

//        ObjectMapper objMapper = new ObjectMapper();
//        try {
//            jmsTemplate.convertAndSend(DESTINATION_NAME, objMapper.writeValueAsString(createdUser));
//        } catch (JsonProcessingException e) {
//            e.printStackTrace();
//        }
        return userService.createUser(userDto);
    }

    @GetMapping("")
    public ResponseEntity<List<UserDto>> getUsers() {
        return ResponseEntity.ok().body(userService.getUsers());
    }

    @GetMapping("/{id}")
    public UserDto getUserById(@PathVariable Long id) {
        return userService.getUserById(id);
    }

    @PostMapping("/add/role")
    public ResponseEntity<?> addRoleToUser(@RequestParam("username") String username, @RequestParam("roleName") String roleName) {
        userService.addRoleToUser(username, roleName);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/delete/role")
    public ResponseEntity<?> deleteRoleFromUser(@RequestParam("username") String username, @RequestParam("roleName") String roleName) {
        userService.deleteRoleFromUser(username, roleName);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return ResponseEntity.ok().body("User with id " + id + " successfully deleted.");
    }

    @PatchMapping("/{id}")
    public UserDto update(@RequestBody UserFullName userFullName, @PathVariable Long id) {
        return userService.update(id, userFullName);
    }

    @GetMapping("/search")
    @ResponseBody
    public ResponseEntity<List<UserDto>> search(@RequestParam("name") String name) {
        return ResponseEntity.ok().body(userService.search(name));
    }
}