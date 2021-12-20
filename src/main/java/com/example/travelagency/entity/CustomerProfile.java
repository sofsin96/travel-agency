package com.example.travelagency.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity @NoArgsConstructor @AllArgsConstructor @Getter @Setter
public class CustomerProfile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String personalIdNo;
    private String phoneNumber;
    private String address;
    private String gender;
//    private String family1;
//    private String family2;

    @OneToOne(fetch = FetchType.LAZY)
    @MapsId
    private Customer customer;
}
