package com.example.graphql.service.datafatcher;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.graphql.model.Vehicle;
import com.example.graphql.repository.VehicleRepository;

import graphql.schema.DataFetcher;
import graphql.schema.DataFetchingEnvironment;

@Component
public class AllVehicleDataFetcher implements DataFetcher<List<Vehicle>>{
	
	@Autowired
	private VehicleRepository vehicleRepository;

	@Override
	public List<Vehicle> get(DataFetchingEnvironment environment) throws Exception {
		// TODO Auto-generated method stub
		return vehicleRepository.findAll();
	}

}
