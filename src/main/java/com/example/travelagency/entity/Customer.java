package com.example.travelagency.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Entity @Getter @Setter
//@JsonIdentityInfo(
//        generator = ObjectIdGenerators.PropertyGenerator.class,
//        property = "id") @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Customer {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String firstName;
    private String lastName;
    private String email;

    @JsonManagedReference
    @OneToOne(mappedBy = "customer",
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY,
            optional = false)
    private CustomerProfile customerProfile;

    @JsonIgnoreProperties({"customerName"})
    @JsonManagedReference
    @OneToMany(mappedBy = "customer",
            cascade = CascadeType.ALL,
            orphanRemoval = true)
    private Set<Booking> bookings;

    public void addItinerary(Booking booking) {
        this.bookings.add(booking);
        booking.setCustomer(this);
    }

    public void removeItinerary(Booking booking) {
        this.bookings.remove(booking);
        booking.setCustomer(null);
    }
}
