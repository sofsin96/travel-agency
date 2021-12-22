package com.example.travelagency.service;

import com.example.travelagency.entity.User;
import com.example.travelagency.entity.Role;
import com.example.travelagency.repository.RoleRepository;
import com.example.travelagency.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service @RequiredArgsConstructor @Transactional
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("User not found in the database.");
        }
        Set<SimpleGrantedAuthority> authorities = new HashSet<>();
        user.getRoles().forEach(role -> authorities.add(new SimpleGrantedAuthority("ROLE_" + role.getName())));
        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), authorities);
    }

    public User createUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.addRole(roleRepository.findByName("USER"));
        return userRepository.save(user);
    }

    public List<User> getUsers() {
        return userRepository.findAll();
    }

    public Optional<User> getUserById(Long id) {
        return userRepository.findById(id);
    }

    public void addRoleToUser(String username, String roleName) {
        User user = userRepository.findByUsername(username);
        Role role = roleRepository.findByName(roleName);
        user.addRole(role);
    }

    public void deleteRoleFromUser(String username, String roleName) {
        User user = userRepository.findByUsername(username);
        Role role = roleRepository.findByName(roleName);
        user.removeRole(role);
    }

    public void deleteUser(Long id) {
        User user = userRepository.findById(id).orElseThrow(EntityNotFoundException::new);
        userRepository.deleteById(user.getId());
    }
}
