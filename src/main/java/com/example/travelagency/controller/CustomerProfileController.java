package com.example.travelagency.controller;

import com.example.travelagency.entity.CustomerProfile;
import com.example.travelagency.service.CustomerProfileService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

import static org.springframework.http.HttpStatus.CREATED;

@RestController @RequestMapping("/api/v1/customer-profiles") @RequiredArgsConstructor
public class CustomerProfileController {

    private final CustomerProfileService customerProfileService;

    @PostMapping("/create")
    @ResponseStatus(CREATED)
    public CustomerProfile createProfile (@Valid @RequestBody CustomerProfile customerProfile) {
        return customerProfileService.createProfile(customerProfile);
    }

    @GetMapping("")
    public ResponseEntity<List<CustomerProfile>> getProfiles() {
        return ResponseEntity.ok().body(customerProfileService.getProfiles());
    }
}
