package com.example.travelagency.service;

import com.example.travelagency.entity.Booking;
import com.example.travelagency.entity.Customer;
import com.example.travelagency.repository.BookingRepository;
import com.example.travelagency.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;

@Service @RequiredArgsConstructor @Transactional
public class CustomerService {

    private final CustomerRepository customerRepository;
    private final BookingRepository bookingRepository;

    public Customer createCustomer(Customer user) {
        return customerRepository.save(user);
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

        booking.ifPresent((Booking b) -> customer.ifPresent(c -> c.addItinerary(b)));
    }

    public void deleteItineraryFromCustomer(Long customerId, Long bookingId) {
        Customer customer = customerRepository.findById(customerId).orElseThrow(EntityNotFoundException::new);
        Booking booking = bookingRepository.findById(bookingId).orElseThrow(EntityNotFoundException::new);
        customer.removeItinerary(booking);
    }

    public void deleteCustomer(Long id) {
        Customer customer = customerRepository.findById(id).orElseThrow(EntityNotFoundException::new);
        customerRepository.deleteById(customer.getId());
    }
}
