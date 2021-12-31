package com.example.travelagency.repository;

import com.example.travelagency.entity.CustomerProfile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerProfileRepository extends JpaRepository<CustomerProfile, Long> {
    CustomerProfile findByPersonalIdNo(String personalIdNo);
}
