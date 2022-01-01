package com.example.travelagency.entity;

import com.fasterxml.jackson.annotation.*;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import java.util.Set;

import static com.fasterxml.jackson.annotation.JsonInclude.*;

@Entity @Setter @Getter
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id")
// @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@JsonInclude(Include.NON_NULL)
public class Customer {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotEmpty(message = "Firstname is mandatory.")
    private String firstName;
    @NotEmpty(message = "Lastname is mandatory.")
    private String lastName;
    @Email
    @NotEmpty(message = "Email is mandatory.")
    private String email;

    @OneToOne(mappedBy = "customer",
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY,
            optional = false)
    @JsonManagedReference
    private CustomerProfile customerProfile;

    @OneToMany(
            mappedBy = "customer",
            cascade = CascadeType.ALL,
            orphanRemoval = true)
    @JsonIgnoreProperties({"customer"})
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
