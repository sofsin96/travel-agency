package com.example.travelagency.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.Set;

@Entity @NoArgsConstructor @Setter
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    private Long id;

    @Getter
    @NotEmpty(message = "Name is mandatory")
    private String name;

    @ManyToMany(mappedBy = "roles")
    @Getter(onMethod_ = @JsonIgnore)
    private Set<User> users;

    public Role(String name) {
        this.name = name;
    }
}
