package com.example.travelagency.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import java.util.Set;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_EMPTY;

@Setter @Getter @JsonInclude(NON_EMPTY)
public class CustomerDto {

    private Long id;

    @NotEmpty(message = "First name is mandatory.")
    private String firstName;

    @NotEmpty(message = "Last name is mandatory.")
    private String lastName;

    @Email(message = "Please provide a valid email.")
    @NotEmpty(message = "Email is mandatory.")
    private String email;

    private CustomerProfileDto customerProfile;

    @JsonIgnoreProperties({"customer"})
    private Set<BookingDto> bookings;
}
