package com.bigrestaurant.system.dishes.model;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.data.mongodb.core.index.GeoSpatialIndexType;
import org.springframework.data.mongodb.core.index.GeoSpatialIndexed;
import org.springframework.data.mongodb.core.mapping.Document;

import com.bigrestaurant.system.modal.GeneralModel;

@Document(collection = "deliverman")
public class DeliverMan extends User implements GeneralModel {

	private int age;
	@GeoSpatialIndexed(name = "lastLocations", type = GeoSpatialIndexType.GEO_2DSPHERE)
	private Coordinates lastLocation;
	private boolean available;

	public DeliverMan(
			@NotNull(message = "user id must be not null") @NotEmpty(message = "user id must be not empty") String id,
			@NotNull(message = "user name must be not null") @NotBlank(message = "user name must be contain char's") @Size(max = 25, min = 5, message = "user name must be with range") String name,
			String pic, String email,
			@NotNull(message = "user phone must be not null") @NotBlank(message = "user phone must be contain numbers") String phone,
			int tableNum, Coordinates location, int age, Coordinates lastLocation, boolean available) {
		super(id, name, pic, email, phone, tableNum, location);
		this.age = age;
		this.lastLocation = lastLocation;
		this.available = available;
	}

	public int getAge() {
		return age;
	}

	public Coordinates getLastLocation() {
		return lastLocation;
	}

	public boolean isAvailable() {
		return available;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public void setLastLocation(Coordinates lastLocation) {
		this.lastLocation = lastLocation;
	}

	public void setAvailable(boolean available) {
		this.available = available;
	}

}
