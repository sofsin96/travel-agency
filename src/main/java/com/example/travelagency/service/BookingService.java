package com.example.travelagency.service;

import com.example.travelagency.dto.BookingDto;
import com.example.travelagency.entity.Booking;
import com.example.travelagency.entity.Destination;
import com.example.travelagency.exception.CustomEntityNotFoundException;
import com.example.travelagency.mapper.BookingMapper;
import com.example.travelagency.repository.BookingRepository;
import com.example.travelagency.repository.DestinationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service @RequiredArgsConstructor
public class BookingService {

    private final BookingRepository bookingRepository;
    private final DestinationRepository destinationRepository;
    private final BookingMapper bookingMapper;

    public BookingDto createBooking(BookingDto bookingDto) {
        return bookingMapper.bookingToBookingDto(bookingRepository.save(bookingMapper.bookingDtoToBooking(bookingDto)));
    }

    public List<BookingDto> getBookings() {
        return bookingRepository.findAll().stream().map(bookingMapper::bookingToBookingDto).collect(Collectors.toList());
    }

    public BookingDto getBookingById(Long id) {
        return bookingMapper.bookingToBookingDto(bookingRepository.findById(id).orElseThrow(() -> new CustomEntityNotFoundException("Booking", id)));
    }

    public void addDestinationToBooking(Long bookingId, Long destinationId) {
        Booking booking = bookingMapper.bookingDtoToBooking(getBookingById(bookingId));
        Destination destination = destinationRepository.findById(destinationId).orElseThrow(() -> new CustomEntityNotFoundException("Destination", destinationId));

        booking.addDestination(destination);
        bookingRepository.save(booking);
    }

    public void deleteDestinationFromBooking(Long bookingId, Long destinationId) {
        Booking booking = bookingMapper.bookingDtoToBooking(getBookingById(bookingId));
        Destination destination = destinationRepository.findById(destinationId).orElseThrow(() -> new CustomEntityNotFoundException("Destination", destinationId));

        booking.removeDestination(destination);
        bookingRepository.save(booking);
    }

    public void deleteBooking(Long id) {
        BookingDto bookingDto = getBookingById(id);
        bookingRepository.deleteById(bookingDto.getId());
    }
}
