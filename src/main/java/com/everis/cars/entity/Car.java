package com.everis.cars.entity;

import java.sql.Timestamp;
import javax.persistence.*;



@Entity
@Table(name="cars")
@NamedQueries({
	@NamedQuery(name = "Car.findAll",query="SELECT c FROM Car c"),
	@NamedQuery(name="Car.findById", query = "SELECT c FROM Car c WHERE c.id= :id")
})

public class Car {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO) 
	@Column(name="id")
	private int id;
	
	@Column(name="brand")
	private String brand;
	
	@Column(name="registration")
	private Timestamp registration;
	
	@Column(name="country")
	private String country; 
	
	@Column(name="created_at")
	private Timestamp createdAt; 
	
	@Column(name="last_updated")
	private Timestamp lastUpdated;

	public Car() {
		
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public Timestamp getRegistration() {
		return registration;
	}

	public void setRegistration(Timestamp registration) {
		this.registration = registration;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public Timestamp getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Timestamp createdAt) {
		this.createdAt = createdAt;
	}

	public Timestamp getLastUpdated() {
		return lastUpdated;
	}

	public void setLastUpdated(Timestamp lastUpdated) {
		this.lastUpdated = lastUpdated;
	} 
	
	public String toString() {
		 return id + " " + brand + " " + registration + " " + createdAt + " " + lastUpdated;
	}
	
	
	
	
	


	
	
	

}
