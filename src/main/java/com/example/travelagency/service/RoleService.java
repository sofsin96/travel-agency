package com.example.travelagency.service;

import com.example.travelagency.dto.RoleDto;
import com.example.travelagency.entity.Role;
import com.example.travelagency.exception.PropertyAlreadyExistException;
import com.example.travelagency.mapper.RoleMapper;
import com.example.travelagency.repository.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service @RequiredArgsConstructor @Transactional
public class RoleService {

    private final RoleRepository roleRepository;
    private final RoleMapper roleMapper;

    public RoleDto createRole(RoleDto roleDto) {
        Role role = roleMapper.roleDtoToRole(roleDto);
        if (roleRepository.existsRoleByName(role.getName())) {
            throw new PropertyAlreadyExistException(role.getName());
        }
        return roleMapper.roleToRoleDto(roleRepository.save(role));
    }

    public List<RoleDto> getRoles() {
        return roleRepository.findAll().stream().map(roleMapper::roleToRoleDto).collect(Collectors.toList());
    }
}