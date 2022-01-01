package com.example.travelagency.controller;

import com.example.travelagency.entity.Booking;
import com.example.travelagency.service.BookingService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

import static org.springframework.http.HttpStatus.CREATED;

@RestController @RequestMapping("/api/v1/bookings") @RequiredArgsConstructor
public class BookingController {

    private final BookingService bookingService;

    @PostMapping("/create")
    @ResponseStatus(CREATED)
    public Booking createBooking(@Valid @RequestBody Booking booking) {
        return bookingService.createBooking(booking);
    }

    @GetMapping("")
    public ResponseEntity<List<Booking>> getBookings() {
        return ResponseEntity.ok().body(bookingService.getBookings());
    }

    @GetMapping("/{id}")
    public Booking getBookingById(@PathVariable Long id) {
        return bookingService.getBookingById(id);
    }

    @PostMapping("/add/booking")
    public ResponseEntity<?> addDestinationToBooking(@RequestParam("bookingId") Long bookingId, @RequestParam("destinationId") Long destinationid) {
        bookingService.addDestinationToBooking(bookingId, destinationid);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/delete/destination")
    public ResponseEntity<?> deleteDestinationFromBooking(@RequestParam("bookingId") Long bookingId, @RequestParam("destinationId") Long destinationid) {
        bookingService.deleteDestinationFromBooking(bookingId, destinationid);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteBooking(@PathVariable Long id) {
        bookingService.deleteBooking(id);
        return ResponseEntity.ok().body("Booking with id " + id + "successfully deleted.");
    }
}
