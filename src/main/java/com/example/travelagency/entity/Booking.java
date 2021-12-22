package com.example.travelagency.entity;

import com.fasterxml.jackson.annotation.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Set;

@Entity @Getter @Setter
//@JsonIdentityInfo(
//        generator = ObjectIdGenerators.PropertyGenerator.class,
//        property = "id")
public class Booking {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private double bookingCost;
    private LocalDate bookingDate;

    @JsonBackReference
    @ManyToOne(fetch = FetchType.LAZY)
    private Customer customer;

    @ManyToMany(fetch = FetchType.EAGER)
    private Set<Destination> destinations;

    @Transient
    private String customerName;

    public String getCustomerName() {
        return getCustomer().getFirstName() + " " + getCustomer().getLastName();
    }
    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

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
