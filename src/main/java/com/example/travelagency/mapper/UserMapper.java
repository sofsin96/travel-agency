package com.example.travelagency.mapper;

import com.example.travelagency.dto.UserDto;
import com.example.travelagency.entity.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = { RoleMapper.class })
public interface UserMapper {

    UserDto userToUserDto(User user);

    User userDtoToUser(UserDto userDto);
}
