package com.everis.cars.entity;

import java.sql.Timestamp;
import javax.persistence.*;
import javax.validation.constraints.*;





@Entity
@Table(name="cars")
@SequenceGenerator(name = "ids", initialValue = 1, allocationSize = 100)
@NamedQueries({
	@NamedQuery(name = "Car.findAll",query="SELECT c FROM Car c")
})

public class Car {
	
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator = "ids") 
	@Column(name="id")
	private int id;
	
	@Column(name="brand")
	@NotNull(message = "Brand cannot be null")
	@Size(min = 1, max = 20, message = "Brand must be between 1-20 characters")
	private String brand;
	
	@Column(name="registration")
	private Timestamp registration;
	
	@Column(name="country")
	@Size(min = 1, max = 20, message = "Country must be between 1-20 characters")
	@NotNull(message="Country cannot be null")
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

	@Override
	public String toString() {
		return "Car [id=" + id + ", brand=" + brand + ", registration=" + registration + ", country=" + country
				+ ", createdAt=" + createdAt + ", lastUpdated=" + lastUpdated + "]";
	} 
	
	
	
	
	
	


	
	
	

}
