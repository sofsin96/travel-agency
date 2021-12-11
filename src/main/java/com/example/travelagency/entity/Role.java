package com.example.travelagency.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Entity @NoArgsConstructor @Setter
public class Role {

    @Id @GeneratedValue(strategy = GenerationType.AUTO) @Getter
    private Long id;

    @Getter
    private String name;

    @ManyToMany(mappedBy = "roles") @Getter(onMethod_ = @JsonIgnore)
    private Set<AppUser> users;

    public Role(Long id, String name) {
        this.id = id;
        this.name = name;
    }
}
