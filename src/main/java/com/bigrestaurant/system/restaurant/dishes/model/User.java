package com.bigrestaurant.system.restaurant.dishes.model;

import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.PersistenceConstructor;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.index.GeoSpatialIndexType;
import org.springframework.data.mongodb.core.index.GeoSpatialIndexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import com.bigrestaurant.system.model.Coordinates;
import com.bigrestaurant.system.model.GeneralModel;
import com.bigrestaurant.system.model.HATEOAS;
import com.bigrestaurant.system.view.View;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonView;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

/**
 * <h3>Description</h3> <br>
 * The restaurant have many of users with attributes such as:
 * 
 * <ul>
 * <li>_id : unique.</li>
 * <li>name : name of user.</li>
 * <li>pic : set user picture.</li>
 * <li>mail : user email.</li>
 * <li>phone : user phone.</li>
 * <li>table : user table if user request dishes in restaurant.</li>
 * <li>location : user location if user request dishes from outside
 * restaurant.</li>
 * </ul>
 * 
 * @author Anas Abu-Hussein
 * @since 12/8/2018
 **/

@Document(collection = "user")
@JsonPropertyOrder({ "userID", "userName", "userMail", "userPhone", "userPic", "table", "location" })
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(Include.NON_EMPTY)
@JsonView(View.UserView.class)

@JacksonXmlRootElement
public class User implements GeneralModel {

	@Id
	@Field("userID")
	@NotNull(message = "user id must be not null")
	@NotEmpty(message = "user id must be not empty")
	@JsonProperty("userID")
	@JsonView(View.CommentsOfDishes.class)
	private String id;

	@Field("userName")
	@NotNull(message = "user name must be not null")
	@NotBlank(message = "user name must be contain char's")
	@Size(max = 25, min = 5, message = "user name must be with range")
	@JsonProperty("userName")
	@JsonView(View.CommentsOfDishes.class)
	private String name;

	@Field("userPic")
	@JsonProperty("userPic")
	@JsonView(View.CommentsOfDishes.class)
	private String pic;

	@Field("userMail")
	@JsonProperty("userMail")
	private String email;

	@NotNull(message = "user phone must be not null")
	@NotBlank(message = "user phone must be contain numbers")
	@Field("userPhone")
	@JsonProperty("userPhone")
	private String phone;

	@Field("table")
	@JsonProperty("table")
	private int tableNum = 0;

	@Field("location")
	@JsonProperty("location")
	@GeoSpatialIndexed(type = GeoSpatialIndexType.GEO_2DSPHERE, name = "location")
	private Coordinates location;

	public User() {
		super();
	}

	@PersistenceConstructor
	public User(
			@NotNull(message = "user id must be not null") @NotEmpty(message = "user id must be not empty") String id,
			@NotNull(message = "user name must be not null") @NotBlank(message = "user name must be contain char's") @Size(max = 25, min = 5, message = "user name must be with range") String name,
			String pic, String email,
			@NotNull(message = "user phone must be not null") @NotBlank(message = "user phone must be contain numbers") String phone,
			Coordinates location) {
		super();
		this.id = id;
		this.name = name;
		this.pic = pic;
		this.email = email;
		this.phone = phone;
		this.location = location;
	}

	@PersistenceConstructor
	public User(
			@NotNull(message = "user id must be not null") @NotEmpty(message = "user id must be not empty") String id,
			@NotNull(message = "user name must be not null") @NotBlank(message = "user name must be contain char's") @Size(max = 25, min = 5, message = "user name must be with range") String name,
			String pic, String email,
			@NotNull(message = "user phone must be not null") @NotBlank(message = "user phone must be contain numbers") String phone,
			int tableNum, Coordinates location) {
		super();
		this.id = id;
		this.name = name;
		this.pic = pic;
		this.email = email;
		this.phone = phone;
		this.tableNum = tableNum;
		this.location = location;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPic() {
		return pic;
	}

	public void setPic(String pic) {
		this.pic = pic;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public int getTableNum() {
		return tableNum;
	}

	public void setTableNum(int tableNum) {
		this.tableNum = tableNum;
	}

	public Coordinates getLocation() {
		return location;
	}

	public void setLocation(Coordinates location) {
		this.location = location;
	}

	public static class Builder {

		private String id;
		private String name;
		private String pic;
		private String email;
		private String phone;
		private int tableNum;
		private Coordinates location;

		private int age;
		private Coordinates lastLocation;
		private boolean available;

		public Builder setId(String id) {
			this.id = id;
			return this;
		}

		public Builder setName(String name) {
			this.name = name;
			return this;
		}

		public Builder setPic(String pic) {
			this.pic = pic;
			return this;
		}

		public Builder setEmail(String email) {
			this.email = email;
			return this;
		}

		public Builder setPhone(String phone) {
			this.phone = phone;
			return this;
		}

		public Builder setTableNum(int tableNum) {
			this.tableNum = tableNum;
			return this;
		}

		public Builder setLocation(Coordinates location) {
			this.location = location;
			return this;
		}

		public Builder setAge(int age) {
			this.age = age;
			return this;
		}

		public Builder setLastLocation(Coordinates lastLocation) {
			this.lastLocation = lastLocation;
			return this;
		}

		public Builder setAvailable(boolean available) {
			this.available = available;
			return this;
		}

		public User buildUser() {
			return new User(id, name, pic, email, phone, tableNum, location);
		}

		public DeliverMan buildDeliverMan() {
			return new DeliverMan(id, name, pic, email, phone, tableNum, location, age, lastLocation, available);
		}

	}

	@JsonProperty("links")
	@JsonView(View.DishesView.class)
	@Transient
	@Override
	public List<HATEOAS> getHATEOAS() {
		List<HATEOAS> links = new ArrayList<>();
		String generalPath = "/api/v1/";

		// get dishes by id ...
		links.add(new HATEOAS(generalPath + "users/" + getId(), "self"));

		// get dishes by name ...
		links.add(new HATEOAS(generalPath + "users/name/" + getName(), "self"));

		return links;
	}

}
