package com.example.travelagency.repository;

import com.example.travelagency.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
    boolean existsUserByUsername(String username);
    List<User> findAllByName(String name);
}