package com.example.travelagency.controller;

import com.example.travelagency.entity.CustomerProfile;
import com.example.travelagency.service.CustomerProfileService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.CREATED;

@RestController @RequestMapping("/api/v1/customerprofiles") @RequiredArgsConstructor
public class CustomerProfileController {

    private final CustomerProfileService customerProfileService;

    @PostMapping("/createprofile")
    @ResponseStatus(CREATED)
    public CustomerProfile createProfile (@RequestBody CustomerProfile customerProfile) {
        return customerProfileService.createProfile(customerProfile);
    }

    @GetMapping("")
    public ResponseEntity<List<CustomerProfile>> getProfiles() {
        return ResponseEntity.ok().body(customerProfileService.getProfiles());
    }
}
