package com.example.travelagency.mapper;

import com.example.travelagency.dto.RoleDto;
import com.example.travelagency.entity.Role;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {UserMapper.class})
public interface RoleMapper {

    @Mapping(target = "users", ignore = true)
    RoleDto roleToRoleDto(Role role);

    @InheritInverseConfiguration(name = "roleToRoleDto")
    Role roleDtoToRole(RoleDto roleDto);
}
