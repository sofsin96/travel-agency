package com.example.travelagency.controller;

import com.example.travelagency.dto.BookingDto;
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
    public BookingDto createBooking(@Valid @RequestBody BookingDto bookingDto) {
        return bookingService.createBooking(bookingDto);
    }

    @GetMapping("")
    public ResponseEntity<List<BookingDto>> getBookings() {
        return ResponseEntity.ok().body(bookingService.getBookings());
    }

    @GetMapping("/{id}")
    public BookingDto getBookingById(@PathVariable Long id) {
        return bookingService.getBookingById(id);
    }

    @PostMapping("/add/destination")
    public ResponseEntity<?> addDestinationToBooking(@RequestParam("bookingId") Long bookingId, @RequestParam("destinationId") Long destinationId) {
        bookingService.addDestinationToBooking(bookingId, destinationId);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/delete/destination")
    public ResponseEntity<?> deleteDestinationFromBooking(@RequestParam("bookingId") Long bookingId, @RequestParam("destinationId") Long destinationId) {
        bookingService.deleteDestinationFromBooking(bookingId, destinationId);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteBooking(@PathVariable Long id) {
        bookingService.deleteBooking(id);
        return ResponseEntity.ok().body("Booking with id " + id + " successfully deleted.");
    }
}
