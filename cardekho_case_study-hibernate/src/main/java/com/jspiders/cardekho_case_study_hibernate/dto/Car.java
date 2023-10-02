//Bhushan Chavan

package com.jspiders.cardekho_case_study_hibernate.dto;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity

public class Car {
	@Id
	private int car_id;
	private String name;
	private String model;
	private String brand;
	private String fuel_type;
	private double price;
	
}

