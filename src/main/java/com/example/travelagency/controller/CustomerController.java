package com.example.travelagency.controller;

import com.example.travelagency.entity.Customer;
import com.example.travelagency.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

import static org.springframework.http.HttpStatus.CREATED;

@RestController @RequestMapping("/api/v1/customers") @RequiredArgsConstructor
public class CustomerController {

    private final CustomerService customerService;

    @PostMapping("/createcustomer")
    @ResponseStatus(CREATED)
    public Customer createCustomer(@Valid @RequestBody Customer customer) {
        return customerService.createCustomer(customer);
    }

    @GetMapping("")
    public ResponseEntity<List<Customer>> getCustomers() {
        return ResponseEntity.ok().body(customerService.getCustomers());
    }

    @GetMapping("/{id}")
    public Customer getCustomerById(@PathVariable Long id) {
        return customerService.getCustomerById(id);
    }

    @PostMapping("/additinerarytocustomer")
    public ResponseEntity<?> addItineraryToCustomer(@RequestParam("customerid") Long customerId, @RequestParam("bookingid") Long bookingId) {
        customerService.addItineraryToCustomer(customerId, bookingId);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/deleteitineraryfromcustomer")
    public ResponseEntity<?> deleteItineraryFromCustomer(@RequestParam("customerid") Long customerId, @RequestParam("bookingid") Long bookingId) {
        customerService.deleteItineraryFromCustomer(customerId, bookingId);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCustomer(@PathVariable Long id) {
        customerService.deleteCustomer(id);
        return ResponseEntity.ok().body("Customer with id " + id + "successfully deleted.");
    }
}
