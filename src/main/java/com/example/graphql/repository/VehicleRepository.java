package com.example.graphql.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.graphql.Vehicle.Vehicle;

public interface VehicleRepository extends JpaRepository<Vehicle, String>{

}
