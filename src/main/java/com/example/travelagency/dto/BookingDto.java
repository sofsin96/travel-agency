package com.example.travelagency.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.DecimalMin;
import java.time.LocalDate;
import java.util.Set;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_EMPTY;

@Setter @Getter @JsonInclude(NON_EMPTY)
public class BookingDto {

    private Long id;

    @DecimalMin(value = "100.0", message = "Error message")
    private double bookingCost;

    private LocalDate bookingDate;

    @JsonIgnoreProperties({"bookings"})
    private CustomerDto customer;

    private Set<DestinationDto> destinations;
}
