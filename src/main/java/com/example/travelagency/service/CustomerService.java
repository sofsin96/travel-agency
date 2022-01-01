package com.example.travelagency.service;

import com.example.travelagency.entity.Booking;
import com.example.travelagency.entity.Customer;
import com.example.travelagency.entity.CustomerProfile;
import com.example.travelagency.exception.CustomEntityNotFoundException;
import com.example.travelagency.exception.PropertyAlreadyExistException;
import com.example.travelagency.repository.BookingRepository;
import com.example.travelagency.repository.CustomerProfileRepository;
import com.example.travelagency.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service @RequiredArgsConstructor
public class CustomerService {

    private final CustomerRepository customerRepository;
    private final BookingRepository bookingRepository;
    private final CustomerProfileRepository customerProfileRepository;

    public Customer createCustomer(Customer customer) {
        CustomerProfile customerProfile = customer.getCustomerProfile();
//        if (checkIfPersonalIdNoExist(customerProfile.getPersonalIdNo())) {
//        throw new PropertyAlreadyExistException(customerProfile.getPersonalIdNo());
//        }
        customerProfile.setCustomer(customer);
        return customerRepository.save(customer);
    }

    public List<Customer> getCustomers() {
        return customerRepository.findAll();
    }

    public Customer getCustomerById(Long id) {
        return customerRepository.findById(id).orElseThrow(() -> new CustomEntityNotFoundException("Customer", id));
    }

    public void addItineraryToCustomer(Long customerId, Long bookingId) {
        Customer customer = getCustomerById(customerId);
        Booking booking = bookingRepository.findById(bookingId).orElseThrow(() -> new CustomEntityNotFoundException("Booking", bookingId));

        customer.addItinerary(booking);
        //customerRepository.flush();
        // TODO: Return Entity
    }

    public void deleteItineraryFromCustomer(Long customerId, Long bookingId) {
        Customer customer = getCustomerById(customerId);
        Booking booking = bookingRepository.findById(bookingId).orElseThrow(() -> new CustomEntityNotFoundException("Booking", bookingId));

        customer.removeItinerary(booking);
        //customerRepository.flush();
        // TODO: Return Entity
    }

    public void deleteCustomer(Long id) {
        Customer customer = getCustomerById(id);
        customerRepository.deleteById(customer.getId());
    }

    public boolean checkIfPersonalIdNoExist(String personalIdNo) {
        return customerProfileRepository.findByPersonalIdNo(personalIdNo) != null;
    }
}
