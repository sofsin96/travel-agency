package com.example.travelagency.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.Set;

import static com.fasterxml.jackson.annotation.JsonInclude.*;

@Entity @Setter @JsonInclude(Include.NON_NULL)
public class Destination {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    private Long id;

    @Getter @NotEmpty(message = "City is mandatory.")
    private String city;

    @Getter @NotEmpty(message = "Country is mandatory.")
    private String country;

    @ManyToMany(mappedBy = "destinations")
    @Getter(onMethod=@__({@JsonIgnore}))
    private Set<Booking> bookings;
}
