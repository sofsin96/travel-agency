package com.example.travelagency.controller;

import com.example.travelagency.dto.DestinationDto;
import com.example.travelagency.service.DestinationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

import static org.springframework.http.HttpStatus.CREATED;

@RestController @RequestMapping("/api/v1/destinations") @RequiredArgsConstructor
public class DestinationController {

    private final DestinationService destinationService;

    @PostMapping("/create")
    @ResponseStatus(CREATED)
    public DestinationDto createDestination(@Valid @RequestBody DestinationDto destinationDto) {
        return destinationService.createDestination(destinationDto);
    }

    @GetMapping("")
    public ResponseEntity<List<DestinationDto>> getDestinations() {
        return ResponseEntity.ok().body(destinationService.getDestinations());
    }
}
