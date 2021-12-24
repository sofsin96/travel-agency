package com.example.travelagency.service;

import com.example.travelagency.entity.*;
import com.example.travelagency.repository.BookingRepository;
import com.example.travelagency.repository.DestinationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;

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

    public Optional<Booking> getBookingById(Long id) {
        return bookingRepository.findById(id);
    }

    public void addDestinationToBooking(Long bookingId, Long destinationId) {
        Optional<Booking> booking = getBookingById(bookingId);
        Optional<Destination> destination = destinationRepository.findById(destinationId);

        destination.ifPresent(d -> booking.ifPresent(b -> b.addDestination(d)));
    }

    public void deleteDestinationFromBooking(Long bookingId, Long destinationId) {
        Booking booking = bookingRepository.findById(bookingId).orElseThrow(EntityNotFoundException::new);
        Destination destination = destinationRepository.findById(destinationId).orElseThrow(EntityNotFoundException::new);
        booking.removeDestination(destination);
    }

    public void deleteBooking(Long id) {
        Booking booking = bookingRepository.findById(id).orElseThrow(EntityNotFoundException::new);
        bookingRepository.deleteById(booking.getId());
    }
}
