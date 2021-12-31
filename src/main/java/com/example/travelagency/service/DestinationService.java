package com.example.travelagency.service;

import com.example.travelagency.entity.Destination;
import com.example.travelagency.repository.DestinationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service @RequiredArgsConstructor
public class DestinationService {

    private final DestinationRepository destinationRepository;

    public Destination createDestination(Destination destination) {
        if (checkIfDestinationExist(destination.getCity())) {
            throw new RuntimeException("City with name " + destination.getCity() + " already exists.");
            // TODO: Create exception
        }
        return destinationRepository.save(destination);
    }

    public List<Destination> getDestinations() {
        return destinationRepository.findAll();
    }

    public boolean checkIfDestinationExist(String city) {
        return destinationRepository.findByCity(city) != null;
    }
}
