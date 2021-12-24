package com.example.travelagency.controller;

import com.example.travelagency.entity.Destination;
import com.example.travelagency.service.DestinationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.CREATED;

@RestController @RequestMapping("/api/v1/destinations") @RequiredArgsConstructor
public class DestinationController {

    private final DestinationService destinationService;

    @PostMapping("/createdestination")
    @ResponseStatus(CREATED)
    public Destination createDestination(@RequestBody Destination destination) {
        return destinationService.createDestination(destination);
    }

    @GetMapping("")
    public ResponseEntity<List<Destination>> getDestinations() {
        return ResponseEntity.ok().body(destinationService.getDestinations());
    }
}
