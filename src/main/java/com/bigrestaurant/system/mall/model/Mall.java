package com.bigrestaurant.system.mall.model;

import java.util.UUID;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.GeoSpatialIndexType;
import org.springframework.data.mongodb.core.index.GeoSpatialIndexed;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import com.bigrestaurant.system.model.Coordinates;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.uuid.Generators;

@Document(collection = "malls")
public class Mall {

	@Id
	@JsonProperty("id")
	private UUID id;

	@NotEmpty
	@NotNull
	@Size(max = 20, min = 3)
	@Indexed(name = "mallName", unique = true)
	@JsonProperty("mallName")
	private String name;

	@NotEmpty
	@NotNull
	@Size(max = 20, min = 3)
	@Indexed(name = "city")
	@JsonProperty("mallCity")
	private String city;

	@NotEmpty
	@NotNull
	@Size(max = 20, min = 3)
	@Indexed(name = "country")
	@JsonProperty("mallCountry")
	private String country;

	@NotEmpty
	@NotNull
	@GeoSpatialIndexed(type = GeoSpatialIndexType.GEO_2DSPHERE)
	@JsonProperty("mallLocation")
	private Coordinates locations;

	public Mall() {
		super();
		if (this.id == null) {
			this.id = Generators.timeBasedGenerator().generate();
		}
	}

	public Mall(@NotEmpty @NotNull @Size(max = 20, min = 3) String name,
			@NotEmpty @NotNull @Size(max = 20, min = 3) String city,
			@NotEmpty @NotNull @Size(max = 20, min = 3) String country, @NotEmpty @NotNull Coordinates locations) {
		super();
		this.id = Generators.timeBasedGenerator().generate();
		this.name = name;
		this.city = city;
		this.country = country;
		this.locations = locations;
	}

	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public Coordinates getLocations() {
		return locations;
	}

	public void setLocations(Coordinates locations) {
		this.locations = locations;
	}

}
