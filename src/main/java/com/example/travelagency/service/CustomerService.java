package com.example.travelagency.service;

import com.example.travelagency.entity.Booking;
import com.example.travelagency.entity.Customer;
import com.example.travelagency.entity.CustomerProfile;
import com.example.travelagency.exception.CustomEntityNotFoundException;
import com.example.travelagency.exception.CustomNotFoundException;
import com.example.travelagency.repository.BookingRepository;
import com.example.travelagency.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;

@Service @RequiredArgsConstructor
public class CustomerService {

    private final CustomerRepository customerRepository;
    private final BookingRepository bookingRepository;

    public Customer createCustomer(Customer customer) {
        CustomerProfile customerProfile = customer.getCustomerProfile();
        customerProfile.setCustomer(customer);
        return customerRepository.save(customer);
    }

    public List<Customer> getCustomers() {
        return customerRepository.findAll();
    }

    public Optional<Customer> getCustomerById(Long id) {
        return customerRepository.findById(id);
    }

    public void addItineraryToCustomer(Long customerId, Long bookingId) {
        Optional<Customer> customer = getCustomerById(customerId);
        Optional<Booking> booking = bookingRepository.findById(bookingId);

        booking.ifPresent(b -> customer.ifPresent(c -> c.addItinerary(b)));
    }

    public void deleteItineraryFromCustomer(Long customerId, Long bookingId) {
        Customer customer = customerRepository.findById(customerId)
                .orElseThrow(() -> new CustomEntityNotFoundException("Customer", customerId));
        Booking booking = bookingRepository.findById(bookingId)
                .orElseThrow(() -> new CustomEntityNotFoundException("Booking", bookingId));
        customer.removeItinerary(booking);
    }

    public void deleteCustomer(Long id) {
        Customer customer = customerRepository.findById(id)
                .orElseThrow(() -> new CustomEntityNotFoundException("Customer", id));
        customerRepository.deleteById(customer.getId());
    }
}
