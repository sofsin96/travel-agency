package com.example.travelagency.view.controller;

import com.example.travelagency.dto.UserDto;
import com.example.travelagency.service.UserService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@Controller @RequiredArgsConstructor
public class RegistrationController {

    private static final String DESTINATION_NAME = "created-user";

    private final UserService userService;
    private final JmsTemplate jmsTemplate;
    private final AuthenticationManager authenticationManager;

    @GetMapping("/signup")
    public String showSignUpForm(Model model) {
        model.addAttribute("user", new UserDto());
        return "signup";
    }

    @PostMapping("/signup")
    public String checkUserInfo(@Valid @ModelAttribute("user") UserDto userDto, BindingResult bindingResult, HttpServletRequest request) {
        if (bindingResult.hasErrors()) {
            return "signup";
        }
        userService.createUser(userDto);

        ObjectMapper objMapper = new ObjectMapper();
        try {
            jmsTemplate.convertAndSend(DESTINATION_NAME, objMapper.writeValueAsString(userDto));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        authWithAuthManager(request, userDto.getUsername(), userDto.getPassword());

        return "redirect:/home";
    }

    private void authWithAuthManager(HttpServletRequest request, String username, String password) {
        UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(username, password);
        authToken.setDetails(new WebAuthenticationDetails(request));

        Authentication authentication = authenticationManager.authenticate(authToken);

        SecurityContextHolder.getContext().setAuthentication(authentication);
    }
}
