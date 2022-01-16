package com.example.travelagency.view.controller;

import com.example.travelagency.dto.UserDto;
import com.example.travelagency.repository.UserRepository;
import com.example.travelagency.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller @RequiredArgsConstructor @RequestMapping("/users")
public class UserMvcController {

    private final UserService userService;

    @GetMapping("")
    public String getAllUsers(Model model) {
        model.addAttribute("users", userService.getUsers());
        return "users";
    }

    @GetMapping("/edit/{id}")
    public String showUpdateForm(@PathVariable("id") Long id, Model model) {
        UserDto userDto = userService.getUserById(id);
        model.addAttribute("user", userDto);

        return "edit-user";
    }

    @PostMapping("/edit/{id}")
    public String updateUser(@PathVariable("id") Long id, @Valid @ModelAttribute("user") UserDto userDto, BindingResult result) {
        if (result.hasErrors()) {
            userDto.setId(id);
            return "edit-user";
        }
        userService.replace(id, userDto);
        return "redirect:/users";
    }

    @GetMapping("delete/{id}")
    public String deleteUser(@PathVariable("id") Long id) {
        UserDto userDto = userService.getUserById(id);
        userService.deleteUser(userDto.getId());
        return "redirect:/users";
    }
}
