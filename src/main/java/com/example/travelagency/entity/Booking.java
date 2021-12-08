package com.example.travelagency.entity;

import javax.persistence.*;
import java.time.LocalDate;

@Entity(name = "Bookings")
public class Booking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long customerId;
    private Long agencyId;
    private double bookingCost;
    private LocalDate bookingDate;

    @ManyToOne
    private Agency agency;

    @PrePersist
    public void getCurrentDate() {
        setBookingDate(LocalDate.now());
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public Long getAgencyId() {
        return agencyId;
    }

    public void setAgencyId(Long agencyId) {
        this.agencyId = agencyId;
    }

    public double getBookingCost() {
        return bookingCost;
    }

    public void setBookingCost(double bookingCost) {
        this.bookingCost = bookingCost;
    }

    public LocalDate getBookingDate() {
        return bookingDate;
    }

    public void setBookingDate(LocalDate bookingDate) {
        this.bookingDate = bookingDate;
    }

    public Agency getAgency() {
        return agency;
    }

    public void setAgency(Agency agency) {
        this.agency = agency;
    }
}
