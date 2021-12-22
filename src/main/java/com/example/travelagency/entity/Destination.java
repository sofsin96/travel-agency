package com.example.travelagency.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Entity @Setter
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
    @Getter(onMethod=@__({@JsonIgnore}))
    private Set<Booking> bookings;
}
