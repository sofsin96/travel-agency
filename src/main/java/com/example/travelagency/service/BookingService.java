package com.example.travelagency.service;

import com.example.travelagency.entity.Booking;
import com.example.travelagency.exception.CustomEntityNotFoundException;
import com.example.travelagency.repository.BookingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service @RequiredArgsConstructor
public class BookingService {

    private final BookingRepository bookingRepository;

    public Booking createBooking(Booking booking) {
        return bookingRepository.save(booking);
    }

    public List<Booking> getBookings() {
        return bookingRepository.findAll();
    }

    public Optional<Booking> getBookingById(Long id) {
        return bookingRepository.findById(id);
    }

    public void deleteBooking(Long id) {
        Booking booking = bookingRepository.findById(id)
                .orElseThrow(() -> new CustomEntityNotFoundException("Booking", id));
        bookingRepository.deleteById(booking.getId());
    }
}
