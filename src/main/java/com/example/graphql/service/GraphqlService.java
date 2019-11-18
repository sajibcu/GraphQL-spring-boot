package com.example.graphql.service;

import java.io.File;
import java.io.IOException;
import java.util.stream.Stream;

import javax.annotation.PostConstruct;
import com.example.graphql.model.Vehicle;
import com.example.graphql.repository.VehicleRepository;
import com.example.graphql.service.datafatcher.AllVehicleDataFetcher;
import com.example.graphql.service.datafatcher.VehicleDataFetcher;


import graphql.GraphQL;
import graphql.schema.GraphQLSchema;
import graphql.schema.idl.RuntimeWiring;
import graphql.schema.idl.SchemaGenerator;
import graphql.schema.idl.SchemaParser;
import graphql.schema.idl.TypeDefinitionRegistry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;


@Service
public class GraphqlService {
	
	private GraphQL graphQL;
	
	@Autowired
	private VehicleRepository vehicleRepository;
	@Autowired
	private AllVehicleDataFetcher allVehicleDataFetcher;
	@Autowired
	private VehicleDataFetcher vehicleDataFetcher;
	
	@Value("classpath:vehicle.graphql")
    Resource resource;
	
	// load schema at application start up
    @PostConstruct
    private void loadSchema() throws IOException {

        //Load Veichle into the Veichle Repository
        loadDataIntoHSQL();

        // get the schema
        File schemaFile = resource.getFile();
        // parse schema
        TypeDefinitionRegistry typeRegistry = new SchemaParser().parse(schemaFile);
        RuntimeWiring wiring = buildRuntimeWiring();
        GraphQLSchema schema = new SchemaGenerator().makeExecutableSchema(typeRegistry, wiring);
        graphQL = GraphQL.newGraphQL(schema).build();
    }
    
    private RuntimeWiring buildRuntimeWiring() {
        return RuntimeWiring.newRuntimeWiring()
                .type("Query", typeWiring -> typeWiring
                        .dataFetcher("allVeichle", allVehicleDataFetcher)
                        .dataFetcher("Veichle", vehicleDataFetcher))
                .build();
    }

    
		
	private void loadDataIntoHSQL() {

        Stream.of(
                new Vehicle("123", "Bus", "Tata",
                        new String[] {
                        "SS","HH"
                        }),
                new Vehicle("223", "Truck", "lata",
                        new String[] {
                        "KK","TT"
                        }),
                new Vehicle("323", "Bus", "pata",
                        new String[] {
                        "BB","CC"
                        })
        ).forEach(vehicle -> {
        	vehicleRepository.save(vehicle);
        });
    }
	
	public GraphQL getGraphQL() {
        return graphQL;
    }

}
