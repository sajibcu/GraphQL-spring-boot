package com.example.graphql.service.datafatcher;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.example.graphql.model.Vehicle;
import com.example.graphql.repository.VehicleRepository;

import graphql.schema.DataFetcher;
import graphql.schema.DataFetchingEnvironment;

@Component
public class VehicleDataFetcher implements DataFetcher<Vehicle>{
	
	@Autowired
	private VehicleRepository vehicleRepository;

	@Override
	public Vehicle get(DataFetchingEnvironment environment) throws Exception {
		// TODO Auto-generated method stub
		String id = environment.getArgument("id");
		Vehicle vehicle = null;
		vehicle = vehicleRepository.findById(id).orElseThrow(() -> new javax.persistence.EntityNotFoundException("No Vehicle found with id: " + id));
		return vehicle;
	}


}
