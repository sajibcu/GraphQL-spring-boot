package com.example.graphql.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.graphql.model.Vehicle;

public interface VehicleRepository extends JpaRepository<Vehicle, String>{
	
	Optional<Vehicle> findOneById(Long id);

}
