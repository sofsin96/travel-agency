package com.example.travelagency.view.controller;

import com.example.travelagency.dto.UserDto;
import com.example.travelagency.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@Controller @RequiredArgsConstructor
public class RegistrationController {

    private final UserService userService;

    @GetMapping("/signup")
    public String showSignUpForm(Model model) {
        model.addAttribute("user", new UserDto());
        return "signup";
    }

    @PostMapping("/signup")
    public String checkUserInfo(@Valid @ModelAttribute("user") UserDto userDto, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "signup";
        }
        userService.createUser(userDto);
        return "redirect:/home";
    }
}
