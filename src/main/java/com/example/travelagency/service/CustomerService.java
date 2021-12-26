package com.example.travelagency.service;

import com.example.travelagency.entity.Booking;
import com.example.travelagency.entity.Customer;
import com.example.travelagency.entity.CustomerProfile;
import com.example.travelagency.repository.BookingRepository;
import com.example.travelagency.repository.CustomerProfileRepository;
import com.example.travelagency.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;

@Service @RequiredArgsConstructor
public class CustomerService {

    private final CustomerRepository customerRepository;
    private final CustomerProfileRepository customerProfileRepository;
    private final BookingRepository bookingRepository;

    public Customer createCustomer(Customer customer) {
//        CustomerProfile customerProfile = customer.getCustomerProfile();
//        customerProfile.setCustomer(customer);
        return customerRepository.save(customer);
    }

    public List<Customer> getCustomers() {
        return customerRepository.findAll();
    }

    public Optional<Customer> getCustomerById(Long id) {
        return customerRepository.findById(id);
    }

//    public void addProfileToCustomer(Long customerId, Long profileId) {
//        Optional<Customer> customer = getCustomerById(customerId);
//        Optional<CustomerProfile> profile = customerProfileRepository.findById(profileId);
//
//        profile.ifPresent(p -> customer.ifPresent(c -> c.setCustomerProfile(p)));
//        customerRepository.flush();
//    }
//
//    public void deleteProfileFromCustomer(Long customerId, Long profileId) {
//        Optional<Customer> customer = getCustomerById(customerId);
//        Optional<CustomerProfile> profile = customerProfileRepository.findById(profileId);
//
//        profile.flatMap(p -> customer).ifPresent(c -> c.setCustomerProfile(null));
//        customerRepository.flush();
//    }

    public void addItineraryToCustomer(Long customerId, Long bookingId) {
        Optional<Customer> customer = getCustomerById(customerId);
        Optional<Booking> booking = bookingRepository.findById(bookingId);

        booking.ifPresent(b -> customer.ifPresent(c -> c.addItinerary(b)));
        customerRepository.flush();
    }

    public void deleteItineraryFromCustomer(Long customerId, Long bookingId) {
        Optional<Customer> customer = getCustomerById(customerId);
        Optional<Booking> booking = bookingRepository.findById(bookingId);

        booking.ifPresent(b -> customer.ifPresent(c -> c.removeItinerary(b)));
        customerRepository.flush();
    }

    public void deleteCustomer(Long id) {
        Customer customer = customerRepository.findById(id).orElseThrow(EntityNotFoundException::new);
        customerRepository.deleteById(customer.getId());
    }
}
