package com.example.travelagency.repository;

import com.example.travelagency.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    Role findByName(String name);
    boolean existsRoleByName(String name);
}
