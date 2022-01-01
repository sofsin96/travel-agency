package com.example.travelagency.service;

import com.example.travelagency.entity.Booking;
import com.example.travelagency.entity.Destination;
import com.example.travelagency.exception.CustomEntityNotFoundException;
import com.example.travelagency.repository.BookingRepository;
import com.example.travelagency.repository.DestinationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service @RequiredArgsConstructor
public class BookingService {

    private final BookingRepository bookingRepository;
    private final DestinationRepository destinationRepository;

    public Booking createBooking(Booking booking) {
        return bookingRepository.save(booking);
    }

    public List<Booking> getBookings() {
        return bookingRepository.findAll();
    }

    public Booking getBookingById(Long id) {
        return bookingRepository.findById(id).orElseThrow(() -> new CustomEntityNotFoundException("Booking", id));
    }

    public void addDestinationToBooking(Long bookingId, Long destinationId) {
        Booking booking = getBookingById(bookingId);
        Destination destination = destinationRepository.findById(destinationId).orElseThrow(() -> new CustomEntityNotFoundException("Destination", destinationId));

        booking.addDestination(destination);
        //bookingRepository.flush();
        // TODO: Return Entity
    }

    public void deleteDestinationFromBooking(Long bookingId, Long destinationId) {
        Booking booking = getBookingById(bookingId);
        Destination destination = destinationRepository.findById(destinationId).orElseThrow(() -> new CustomEntityNotFoundException("Destination", destinationId));

        booking.removeDestination(destination);
        //bookingRepository.flush();
        // TODO: Return Entity
    }

    public void deleteBooking(Long id) {
        Booking booking = getBookingById(id);
        bookingRepository.deleteById(booking.getId());
    }
}
