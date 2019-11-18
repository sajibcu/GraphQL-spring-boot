package com.example.graphql.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.graphql.service.GraphqlService;

import graphql.ExecutionResult;

@RestController
@RequestMapping("/api")
public class VehicleResource {
	
	@Autowired
	private GraphqlService graphqlService;
	
	
	
	@PostMapping(value = "/vehicles", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> getAllVehicle(@RequestBody String query) {
        ExecutionResult execute = graphqlService.getGraphQL().execute(query);

        return new ResponseEntity<>(execute, HttpStatus.OK);
    }
	

}
