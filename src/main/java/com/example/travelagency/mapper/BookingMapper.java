package com.example.travelagency.mapper;

import com.example.travelagency.dto.BookingDto;
import com.example.travelagency.entity.Booking;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {CustomerMapper.class, DestinationMapper.class})
public interface BookingMapper {

    @Mapping(target = "customer.bookings", ignore = true)
    BookingDto bookingToBookingDto(Booking booking);

    @InheritInverseConfiguration(name = "bookingToBookingDto")
    Booking bookingDtoToBooking(BookingDto bookingDto);
}
