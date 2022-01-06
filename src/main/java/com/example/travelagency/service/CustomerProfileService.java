package com.example.travelagency.service;

import com.example.travelagency.dto.CustomerProfileDto;
import com.example.travelagency.entity.Customer;
import com.example.travelagency.entity.CustomerProfile;
import com.example.travelagency.mapper.CustomerProfileMapper;
import com.example.travelagency.repository.CustomerProfileRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service @RequiredArgsConstructor
public class CustomerProfileService {

    private final CustomerProfileRepository customerProfileRepository;
    private final CustomerProfileMapper customerProfileMapper;

    public CustomerProfileDto createProfile(CustomerProfileDto customerProfileDto) {
        CustomerProfile customerProfile = customerProfileMapper.customerProfileDtoToCustomerProfile(customerProfileDto);
        Customer customer = customerProfile.getCustomer();
        customerProfile.setCustomer(customer);
        return customerProfileMapper.customerProfileToCustomerProfileDto(customerProfileRepository.save(customerProfile));
    }

    public List<CustomerProfileDto> getProfiles() {
        return customerProfileRepository.findAll().stream().map(customerProfileMapper::customerProfileToCustomerProfileDto).collect(Collectors.toList());
    }
}
