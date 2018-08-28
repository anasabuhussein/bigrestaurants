package com.bigrestaurant.system.restaurant.modal;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.PersistenceConstructor;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.geo.GeoJsonPoint;
import org.springframework.data.mongodb.core.index.GeoSpatialIndexType;
import org.springframework.data.mongodb.core.index.GeoSpatialIndexed;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import com.bigrestaurant.system.dishes.model.Dishes;
import com.bigrestaurant.system.dishes.view.View;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonView;
import com.fasterxml.uuid.Generators;

@Document(collection = "restaurant")
@JsonView(View.RestaurantView.class)
public class Restaurant {

	@Id
	@JsonProperty("id")
	private UUID id;

	@NotEmpty
	@NotNull
	@Size(max = 20, min = 3)
	@Indexed(name = "restName",unique = true)
	@JsonProperty("restName")
	private String restName;

	@NotEmpty
	@NotNull
	@Size(max = 20, min = 3)
	@Indexed(name = "country")
	@JsonProperty("country")
	private String country;

	@NotEmpty
	@NotNull
	@Size(max = 20, min = 3)
	@Indexed(name = "city")
	@JsonProperty("city")
	private String city;

	@Transient
	private List<Dishes> dishes = new ArrayList<Dishes>();

	@NotEmpty
	@NotNull
	@GeoSpatialIndexed(type = GeoSpatialIndexType.GEO_2DSPHERE)
	private GeoJsonPoint location;

	public Restaurant() {
		super();
		if (this.id == null) {
			this.id = Generators.timeBasedGenerator().generate();
		}
	}

	@PersistenceConstructor
	public Restaurant(@NotEmpty @NotNull @Size(max = 20, min = 3) String restName,
			@NotEmpty @NotNull @Size(max = 20, min = 3) String country,
			@NotEmpty @NotNull @Size(max = 20, min = 3) String city, @NotEmpty @NotNull GeoJsonPoint location) {
		super();
		this.id = Generators.timeBasedGenerator().generate();
		this.restName = restName;
		this.country = country;
		this.city = city;
		this.location = location;
	}

	/**
	 * @return the id
	 */
	public UUID getId() {
		return id;
	}

	/**
	 * @return the restName
	 */
	public String getRestName() {
		return restName;
	}

	/**
	 * @return the country
	 */
	public String getCountry() {
		return country;
	}

	/**
	 * @return the city
	 */
	public String getCity() {
		return city;
	}

	/**
	 * @return the location
	 */
	public GeoJsonPoint getLocation() {
		return location;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(UUID id) {
		this.id = id;
	}

	/**
	 * @param restName the restName to set
	 */
	public void setRestName(String restName) {
		this.restName = restName;
	}

	/**
	 * @param country the country to set
	 */
	public void setCountry(String country) {
		this.country = country;
	}

	/**
	 * @param city the city to set
	 */
	public void setCity(String city) {
		this.city = city;
	}

	/**
	 * @param location the location to set
	 */
	public void setLocation(GeoJsonPoint location) {
		this.location = location;
	}

	public List<Dishes> getDishes() {
		return dishes;
	}

	public void setDishes(List<Dishes> dishes) {
		this.dishes = dishes;
	}

}
