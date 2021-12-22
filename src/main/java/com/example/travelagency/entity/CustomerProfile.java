package com.example.travelagency.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity @Getter @Setter
public class CustomerProfile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String personalIdNo;
    private String phoneNumber;
    private String address;
    private String gender;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id")
    @JsonBackReference
    private Customer customer;
}
