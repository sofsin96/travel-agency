package com.example.travelagency.mapper;

import com.example.travelagency.dto.DestinationDto;
import com.example.travelagency.entity.Destination;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {BookingMapper.class})
public interface DestinationMapper {

    @Mapping(target = "bookings", ignore = true)
    DestinationDto destinationToDestinationDto(Destination destination);

    @InheritInverseConfiguration(name = "destinationToDestinationDto")
    Destination destinationDtoToDestination(DestinationDto destinationDto);
}
