package com.example.travelagency.controller;

import com.example.travelagency.entity.Customer;
import com.example.travelagency.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.NOT_FOUND;

@RestController @RequestMapping("/api/v1/customers") @RequiredArgsConstructor
public class CustomerController {

    private final CustomerService customerService;

    @PostMapping("")
    @ResponseStatus(CREATED)
    public Customer createCustomer(@RequestBody Customer customer) {
        return customerService.createUser(customer);
    }

    @GetMapping("")
    public ResponseEntity<List<Customer>> getCustomers() {
        return ResponseEntity.ok().body(customerService.getCustomers());
    }

    @GetMapping("/{id}")
    public Customer getCustomerById(@PathVariable Long id) {
        return customerService.getCustomerById(id)
                .orElseThrow(() -> new ResponseStatusException(NOT_FOUND,
                        "Customer with id " + id + " not found."));
    }

    @PostMapping("/addroletouser")
    public ResponseEntity<Customer> addItineraryToCustomer(@RequestParam Long customerId, @RequestParam Long bookingId) {
        customerService.addItineraryToCustomer(customerId, bookingId);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/deleterolefromuser")
    public ResponseEntity<Customer> deleteItineraryFromCustomer(@RequestParam Long customerId, @RequestParam Long bookingId) {
        customerService.deleteItineraryFromCustomer(customerId, bookingId);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public void deleteCustomer(@PathVariable Long id) {
        customerService.deleteCustomer(id);
    }
}
