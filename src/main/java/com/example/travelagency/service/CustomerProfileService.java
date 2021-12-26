package com.example.travelagency.service;

import com.example.travelagency.entity.Customer;
import com.example.travelagency.entity.CustomerProfile;
import com.example.travelagency.repository.CustomerProfileRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service @RequiredArgsConstructor
public class CustomerProfileService {

    private final CustomerProfileRepository customerProfileRepository;

    public CustomerProfile createProfile(CustomerProfile customerProfile) {
//        Customer customer = customerProfile.getCustomer();
//        customerProfile.setCustomer(customer);
        return customerProfileRepository.save(customerProfile);
    }

    public List<CustomerProfile> getProfiles() {
        return customerProfileRepository.findAll();
    }
}
