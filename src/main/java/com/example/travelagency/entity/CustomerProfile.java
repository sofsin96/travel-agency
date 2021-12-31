package com.example.travelagency.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

@Entity @Setter @Getter
public class CustomerProfile {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty(message = "Personal identity number is mandatory.")
    private String personalIdNo;
    private String phoneNumber;
    private String address;
    private String gender;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id")
    @JsonBackReference
    private Customer customer;
}
