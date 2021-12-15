package com.example.travelagency.service;

import com.example.travelagency.entity.AppUser;
import com.example.travelagency.entity.Role;

import java.util.List;

public interface UserService {
    AppUser saveUser(AppUser user);
    Role saveRole(Role role);
    void addRoleToUser(String username, String roleName);
    List<AppUser> getUsers();
}
