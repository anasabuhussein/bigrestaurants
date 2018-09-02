package com.bigrestaurant.system.dishes.model;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.PersistenceConstructor;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import com.bigrestaurant.system.modal.GeneralModel;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.uuid.Generators;

/**
 * <h3>Description</h3> <br>
 * - The restaurant save user transaction from requests and component; The user
 * can request many dishes.<br>
 * <br>
 * 
 * @author Anas Abu-Hussein
 * @since 12/8/2018
 **/

//* - This transaction have attributes such as:
//* 
//* <ul>
//* <li>_id : unique transaction id === userID + dishesID + date of request
//* .</li>
//* <li>user : the user request the dishes.</li>
//* <li>rating : total rating that coming from user his get like from 10.</li>
//* <li>pic : set dishes picture by restaurant.</li>
//* <li>main component list : the restaurant describe dishes receipt.</li>
//* <li>additional component list : the user add another dishes receipt.</li>
//* </ul>
//* 

@Document(collection = "orders")
@JsonInclude(Include.NON_EMPTY)
public class Orders implements GeneralModel {

	@Id
	private final UUID id;

	@NotNull
	@JsonProperty("user")
	private User user;

	@NotNull
	@NotEmpty
	@JsonProperty("dishes")
	private Set<Dishes> dishes = new HashSet<>();

	@Max(10)
	@Min(0)
	@JsonProperty("userRating")
	private long userRating;

	@Field("approval")
	@JsonProperty("approval")
	private boolean approval;

	@Field("delever")
	@JsonProperty("delever")
	private boolean delever;

	@Field("done")
	@JsonProperty("done")
	private boolean done;

	@Field(value = "orderDate")
	@JsonProperty("orderDate")
	private OrderDate date;

	@PersistenceConstructor
	public Orders(@NotNull User user, @NotNull @NotEmpty Set<Dishes> dishes) {
		super();
		this.id = Generators.timeBasedGenerator().generate();
		this.user = user;
		this.dishes = dishes;

		date = OrderDate.orderDate();
	}

	@PersistenceConstructor
	public Orders(@NotNull User user, @NotNull @NotEmpty Set<Dishes> dishes, @Max(10) @Min(0) long userRating) {
		super();
		this.id = Generators.timeBasedGenerator().generate();
		this.user = user;
		this.dishes = dishes;
		this.userRating = userRating;

		date = OrderDate.orderDate();
	}

	public Orders(@NotNull User user, @NotNull @NotEmpty Set<Dishes> dishes, @Max(10) @Min(0) long userRating,
			boolean approval, boolean delever) {
		super();
		this.id = Generators.timeBasedGenerator().generate();
		this.user = user;
		this.dishes = dishes;
		this.userRating = userRating;
		this.approval = approval;
		this.delever = delever;
		date = OrderDate.orderDate();
	}

	public void setUserRating(long userRating) {
		this.userRating = userRating;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public void setDishes(Set<Dishes> dishes) {
		this.dishes = dishes;
	}

	public UUID getId() {
		return id;
	}

	public long getUserRating() {
		return userRating;
	}

	public User getUser() {
		return user;
	}

	public boolean isApproval() {
		return approval;
	}

	public void setApproval(boolean approval) {
		this.approval = approval;
	}

	public boolean isDelever() {
		return delever;
	}

	public void setDelever(boolean delever) {
		this.delever = delever;
	}

	public Set<Dishes> getDishes() {
		return dishes;
	}

	public boolean isDone() {
		return done;
	}

	public void setDone(boolean done) {
		this.done = done;
	}

	public OrderDate getDate() {
		return date;
	}

	public void setDate(OrderDate date) {
		this.date = date;
	}

	@Override
	public List<HATEOAS> getHATEOAS() {
		return null;
	}

}
