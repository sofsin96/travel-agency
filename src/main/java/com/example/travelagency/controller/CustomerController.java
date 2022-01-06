package com.example.travelagency.controller;

import com.example.travelagency.dto.CustomerDto;
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

    @PostMapping("/create")
    @ResponseStatus(CREATED)
    public CustomerDto createCustomer(@Valid @RequestBody CustomerDto customerDto) {
        return customerService.createCustomer(customerDto);
    }

    @GetMapping("")
    public ResponseEntity<List<CustomerDto>> getCustomers() {
        return ResponseEntity.ok().body(customerService.getCustomers());
    }

    @GetMapping("/{id}")
    public CustomerDto getCustomerById(@PathVariable Long id) {
        return customerService.getCustomerById(id);
    }

    @PostMapping("/add/itinerary")
    public ResponseEntity<?> addItineraryToCustomer(@RequestParam("customerId") Long customerId, @RequestParam("bookingId") Long bookingId) {
        customerService.addItineraryToCustomer(customerId, bookingId);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/delete/itinerary")
    public ResponseEntity<?> deleteItineraryFromCustomer(@RequestParam("customerId") Long customerId, @RequestParam("bookingId") Long bookingId) {
        customerService.deleteItineraryFromCustomer(customerId, bookingId);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCustomer(@PathVariable Long id) {
        customerService.deleteCustomer(id);
        return ResponseEntity.ok().body("Customer with id " + id + " successfully deleted.");
    }
}
