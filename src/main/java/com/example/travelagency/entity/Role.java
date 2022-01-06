package com.example.travelagency.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.StringUtils;

import javax.persistence.*;
import java.util.Set;

@Entity @Setter
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    private Long id;

    @Getter
    private String name;

    @ManyToMany(mappedBy = "roles")
    @Getter(onMethod=@__({@JsonIgnore}))
    private Set<User> users;

    @PrePersist
    public void prePersistFunction() {
        if (!StringUtils.isAllUpperCase(name)) {
            name = name.toUpperCase();
        }
    }
}
