package com.example.travelagency.service;

import com.example.travelagency.dto.CustomerDto;
import com.example.travelagency.entity.Booking;
import com.example.travelagency.entity.Customer;
import com.example.travelagency.entity.CustomerProfile;
import com.example.travelagency.exception.CustomEntityNotFoundException;
import com.example.travelagency.mapper.CustomerMapper;
import com.example.travelagency.repository.BookingRepository;
import com.example.travelagency.repository.CustomerProfileRepository;
import com.example.travelagency.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service @RequiredArgsConstructor
public class CustomerService {

    private final CustomerRepository customerRepository;
    private final BookingRepository bookingRepository;
    private final CustomerProfileRepository customerProfileRepository;
    private final CustomerMapper customerMapper;

    public CustomerDto createCustomer(CustomerDto customerDto) {
        Customer customer = customerMapper.customerDtoToCustomer(customerDto);
        CustomerProfile customerProfile = customer.getCustomerProfile();
        customerProfile.setCustomer(customer);
        return customerMapper.customerToCustomerDto(customerRepository.save(customer));
    }

    public List<CustomerDto> getCustomers() {
        return customerRepository.findAll().stream().map(customerMapper::customerToCustomerDto).collect(Collectors.toList());
    }

    public CustomerDto getCustomerById(Long id) {
        return customerMapper.customerToCustomerDto(customerRepository.findById(id).orElseThrow(() -> new CustomEntityNotFoundException("Customer", id)));
    }

    public void addItineraryToCustomer(Long customerId, Long bookingId) {
        Customer customer = customerMapper.customerDtoToCustomer(getCustomerById(customerId));
        Booking booking = bookingRepository.findById(bookingId).orElseThrow(() -> new CustomEntityNotFoundException("Booking", bookingId));

        customer.addItinerary(booking);
        customerRepository.save(customer);
    }

    public void deleteItineraryFromCustomer(Long customerId, Long bookingId) {
        Customer customer = customerMapper.customerDtoToCustomer(getCustomerById(customerId));
        Booking booking = bookingRepository.findById(bookingId).orElseThrow(() -> new CustomEntityNotFoundException("Booking", bookingId));

        customer.removeItinerary(booking);
        customerRepository.save(customer);
    }

    public void deleteCustomer(Long id) {
        CustomerDto customerDto = getCustomerById(id);
        customerRepository.deleteById(customerDto.getId());
    }

    public boolean checkIfPersonalIdNoExist(String personalIdNo) {
        return customerProfileRepository.findByPersonalIdNo(personalIdNo) != null;
    }
}
