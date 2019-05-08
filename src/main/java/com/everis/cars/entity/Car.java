package com.everis.cars.entity;

import java.sql.Timestamp;
import javax.persistence.*;
import javax.validation.constraints.*;
import javax.ws.rs.DefaultValue;

@Entity
@Table(name = "cars")
@NamedQueries(value = {
	@NamedQuery(name = "Car.findAll", query = "select c from Car c where c.softDeleted = false"),
	@NamedQuery(name = "Car.findById", query = "select c from Car c where c.softDeleted = false AND c.id = :id"),
	@NamedQuery(name = "Car.findAllDeleted", query = "select c from Car c where c.softDeleted = true")
}
)
public class Car {	
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID")
    @NotNull
    private int id;

    @Column(name = "BRAND")
    @NotNull(message = "Brand cannot be null")
    @Size(min = 1, max = 20, message = "Brand must be between 1-20 characters")
    private String brand;

    @Column(name = "REGISTRATION")
    private Timestamp registration;

    @Column(name = "COUNTRY")
    @Size(min = 1, max = 20, message = "Country must be between 1-20 characters")
    @NotNull(message = "Country cannot be null")
    private String country;

    @Column(name = "DELETED_AT")
    private Timestamp softDeleted_at;

    @Column(name = "SOFT_DELETED")
    @DefaultValue(value = "false")
    private boolean softDeleted;

    @Column(name = "CREATED_AT")
    private Timestamp createdAt;

    @Column(name = "LAST_UPDATED")
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

    public Timestamp getSoftDeleted_at() {
	return softDeleted_at;
    }

    public void setSoftDeleted_at(Timestamp softDeleted_at) {
	this.softDeleted_at = softDeleted_at;
    }

    public boolean isSoftDeleted() {
	return softDeleted;
    }

    public void setSoftDeleted(boolean softDeleted) {
	this.softDeleted = softDeleted;
    }

    @Override
    public String toString() {
	return "Car [id=" + id + ", brand=" + brand + ", registration=" + registration + ", country=" + country
		+ ", softDeleted_at=" + softDeleted_at + ", softDeleted=" + softDeleted + ", createdAt=" + createdAt
		+ ", lastUpdated=" + lastUpdated + "]";
    }

    

}
