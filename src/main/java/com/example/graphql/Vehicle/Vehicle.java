package com.example.graphql.Vehicle;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Table
@Entity
public class Vehicle {
	
	@Id 
	private String id;
	private String title;
	private String model;
	private String[] owner;

}
