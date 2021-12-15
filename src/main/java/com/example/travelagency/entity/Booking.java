package com.example.travelagency.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Set;

@Entity @NoArgsConstructor @AllArgsConstructor @Getter @Setter
public class Booking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private double bookingCost;
    private LocalDate bookingDate;

    @ManyToOne(fetch = FetchType.LAZY)
    private Customer customer;

    @ManyToMany(fetch = FetchType.EAGER)
    private Set<Destination> destinations;

    @PrePersist
    public void getCurrentDate() {
        setBookingDate(LocalDate.now());
    }

    public void addDestination(Destination destination) {
        this.destinations.add(destination);
        destination.getBookings().add(this);
    }

    public void removeDestination(Destination destination) {
        this.destinations.remove(destination);
        destination.getBookings().remove(this);
    }
}
