package com.example.travelagency.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.Set;

import static com.fasterxml.jackson.annotation.JsonInclude.Include;

@NoArgsConstructor
@Getter
@Setter
@JsonInclude(Include.NON_EMPTY)
public class UserDto {

    private Long id;

    private String name;

    @NotEmpty(message = "Username is mandatory.")
    @Size(min = 2, message = "Username must be at least 2 characters long.")
    private String username;

    @NotEmpty(message = "Password is mandatory.")
    private String password;

    private Set<RoleDto> roles;

    public UserDto(String name, String username, String password) {
        this.name = name;
        this.username = username;
        this.password = password;
        this.roles = new HashSet<>();
    }

    @Override
    public String toString() {
        return "UserDto{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", roles=" + roles +
                '}';
    }
}
