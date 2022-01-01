package com.example.travelagency.entity;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.Set;

@Entity @Setter @Getter @JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id")
public class Booking {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @DecimalMin(value = "100.0", message = "Error message")
    private double bookingCost;
    private LocalDate bookingDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id")
    @JsonIgnoreProperties({"bookings"})
    private Customer customer;

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
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
