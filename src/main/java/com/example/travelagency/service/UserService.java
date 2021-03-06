package com.example.travelagency.service;

import com.example.travelagency.security.UserPrincipal;
import com.example.travelagency.dto.UserDto;
import com.example.travelagency.dto.UserFullName;
import com.example.travelagency.entity.Role;
import com.example.travelagency.entity.User;
import com.example.travelagency.exception.CustomEntityNotFoundException;
import com.example.travelagency.exception.CustomNameNotFoundException;
import com.example.travelagency.exception.PropertyAlreadyExistException;
import com.example.travelagency.mapper.UserMapper;
import com.example.travelagency.repository.RoleRepository;
import com.example.travelagency.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service @RequiredArgsConstructor @Transactional
public class UserService implements UserDetailsService {

    private final static String USER_NOT_FOUND_MSG = "User with username %s not found in the database.";

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserMapper userMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException(String.format(USER_NOT_FOUND_MSG, username));
        }
        return new UserPrincipal(user);
    }

    public UserDto createUser(UserDto userDto) {
        User user = userMapper.userDtoToUser(userDto);

        if (userRepository.existsUserByUsername(user.getUsername())) {
            throw new PropertyAlreadyExistException(user.getUsername());
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.addRole(roleRepository.findByName("USER"));
        return userMapper.userToUserDto(userRepository.save(user));
    }

    public List<UserDto> getUsers() {
        return userRepository.findAll().stream().map(userMapper::userToUserDto).collect(Collectors.toList());
    }

    public UserDto getUserById(Long id) {
        return userMapper.userToUserDto(userRepository.findById(id).orElseThrow(() -> new CustomEntityNotFoundException("User", id)));
    }

    public void addRoleToUser(String username, String roleName) {
        User user = getUser(username);
        Role role = getRole(roleName);
        user.addRole(role);
    }

    public void deleteRoleFromUser(String username, String roleName) {
        User user = getUser(username);
        Role role = getRole(roleName);
        user.removeRole(role);
    }

    public void deleteUser(Long id) {
        UserDto userDto = getUserById(id);
        userRepository.deleteById(userDto.getId());
    }

    public void replace(Long id, UserDto userDto) {
        Optional<User> user = userRepository.findById(id);
        if (user.isPresent()) {
            User updatedUser = user.get();
            updatedUser.setName(userDto.getName());
            updatedUser.setUsername(userDto.getUsername());
            updatedUser.setPassword(passwordEncoder.encode(userDto.getPassword()));
            userMapper.userToUserDto(userRepository.save(updatedUser));
        }
        else
            throw new CustomEntityNotFoundException("User", id);
    }

    public UserDto update(Long id, UserFullName userFullName) {
        Optional<User> user = userRepository.findById(id);
        if (user.isPresent()) {
            User updatedUser = user.get();
            if (userFullName.fullName != null)
                updatedUser.setName(userFullName.fullName);
            return userMapper.userToUserDto(userRepository.save(updatedUser));
        }
        else
            throw new CustomEntityNotFoundException("User", id);
    }

    public List<UserDto> search(String name) {
        return userRepository.findAllByName(name).stream().map(userMapper::userToUserDto).collect(Collectors.toList());
    }

    private User getUser(String username) {
        User user = userRepository.findByUsername(username);
        if (user == null) {
            throw new CustomNameNotFoundException("Username", username);
        }
        return user;
    }

    private Role getRole(String roleName) {
        Role role = roleRepository.findByName(roleName);
        if (role == null)
            throw new CustomNameNotFoundException("Role name", roleName);
        return role;
    }
}
