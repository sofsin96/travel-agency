package com.example.travelagency.entity;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
public class Booking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long customerId;
    private Long agencyId;
    private double bookingCost;
    private LocalDate bookingDate;
}
