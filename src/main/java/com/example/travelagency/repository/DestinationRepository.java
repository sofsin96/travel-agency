package com.example.travelagency.repository;

import com.example.travelagency.entity.Destination;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DestinationRepository extends JpaRepository<Destination, Long> {
    Destination findByCity(String city);
    boolean existsDestinationByCity(String city);
}
