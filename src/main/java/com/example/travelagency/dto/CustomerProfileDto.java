package com.example.travelagency.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;

import static com.fasterxml.jackson.annotation.JsonInclude.Include;

@Setter @Getter @JsonInclude(Include.NON_EMPTY)
public class CustomerProfileDto {

    private Long id;

    @NotEmpty(message = "Personal identity number is mandatory.")
    private String personalIdNo;

    private String phoneNumber;
    private String address;
    private String gender;
    private CustomerDto customer;
}
