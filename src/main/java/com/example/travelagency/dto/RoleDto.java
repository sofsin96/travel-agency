package com.example.travelagency.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import java.util.Set;

@Setter
@NoArgsConstructor
public class RoleDto {

    @Getter
    private Long id;

    @Getter
    @NotEmpty(message = "Name is mandatory.")
    private String name;

    @Getter(onMethod=@__({@JsonIgnore}))
    private Set<UserDto> users;

    public RoleDto(String name) {
        this.name = name;
    }
}
