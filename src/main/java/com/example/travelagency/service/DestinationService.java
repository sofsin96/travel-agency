package com.example.travelagency.service;

import com.example.travelagency.dto.DestinationDto;
import com.example.travelagency.entity.Destination;
import com.example.travelagency.exception.PropertyAlreadyExistException;
import com.example.travelagency.mapper.DestinationMapper;
import com.example.travelagency.repository.DestinationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service @RequiredArgsConstructor
public class DestinationService {

    private final DestinationRepository destinationRepository;
    private final DestinationMapper destinationMapper;

    public DestinationDto createDestination(DestinationDto destinationDto) {
        Destination destination = destinationMapper.destinationDtoToDestination(destinationDto);

        if (destinationRepository.existsDestinationByCity(destination.getCity())) {
            throw new PropertyAlreadyExistException(destination.getCity());
        }
        return destinationMapper.destinationToDestinationDto(destinationRepository.save(destination));
    }

    public List<DestinationDto> getDestinations() {
        return destinationRepository.findAll().stream().map(destinationMapper::destinationToDestinationDto).collect(Collectors.toList());
    }
}
