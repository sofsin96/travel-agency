package com.example.travelagency.service;

import com.example.travelagency.entity.Role;
import com.example.travelagency.exception.PropertyAlreadyExistException;
import com.example.travelagency.repository.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service @RequiredArgsConstructor @Transactional
public class RoleService {

    private final RoleRepository roleRepository;

    public Role createRole(Role role) {
        if (checkIfRoleExist(role.getName())) {
            throw new PropertyAlreadyExistException(role.getName());
        }
        return roleRepository.save(role);
    }

    public List<Role> getRoles() {
        return roleRepository.findAll();
    }

    public boolean checkIfRoleExist(String roleName) {
        return roleRepository.findByName(roleName) != null;
    }
}