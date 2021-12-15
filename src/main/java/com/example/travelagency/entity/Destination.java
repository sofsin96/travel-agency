package com.example.travelagency.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Entity @NoArgsConstructor @AllArgsConstructor @Setter
public class Destination {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    private Long id;

    @Getter
    private String city;

    @Getter
    private String country;

    @ManyToMany(mappedBy = "destinations")
    @Getter(onMethod_ = @JsonIgnore)
    private Set<Booking> bookings;
}
