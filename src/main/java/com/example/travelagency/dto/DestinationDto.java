package com.example.travelagency.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import java.util.Set;

@Setter
public class DestinationDto {

    @Getter
    private Long id;

    @Getter @NotEmpty(message = "City is mandatory.")
    private String city;

    @Getter @NotEmpty(message = "Country is mandatory.")
    private String country;

    @Getter(onMethod=@__({@JsonIgnore}))
    private Set<BookingDto> bookings;
}
