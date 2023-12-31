package com.example.airportProject.repositories;

import com.example.airportProject.model.Aircraft;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AircraftRepository extends JpaRepository<Aircraft, String> {

    Optional<Aircraft> findByRegistration(String registration);

}
