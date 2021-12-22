package com.example.travelagency.controller;

import com.example.travelagency.entity.Booking;
import com.example.travelagency.service.BookingService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.NOT_FOUND;

@RestController @RequestMapping("/api/v1/bookings") @RequiredArgsConstructor
public class BookingController {

    private final BookingService bookingService;

    @PostMapping("/createbooking")
    @ResponseStatus(CREATED)
    public Booking createBooking(@RequestBody Booking booking) {
        return bookingService.createBooking(booking);
    }

    @GetMapping("")
    public ResponseEntity<List<Booking>> getBookings() {
        return ResponseEntity.ok().body(bookingService.getBookings());
    }

    @GetMapping("/{id}")
    public Booking getBookingById(@PathVariable Long id) {
        return bookingService.getBookingById(id)
                .orElseThrow(() -> new ResponseStatusException(NOT_FOUND,
                        "Booking with id " + id + " not found."));
    }

    @DeleteMapping("/{id}")
    public void deleteBooking(@PathVariable Long id) {
        bookingService.deleteBooking(id);
    }
}
