package com.bigrestaurant.system.mall.model;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.validation.constraints.NotNull;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.PersistenceConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

import com.bigrestaurant.system.model.OrderDate;
import com.bigrestaurant.system.restaurant.dishes.model.User;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.uuid.Generators;

@Document(collection = "mallOrders")
public class MallOrders {

	@Id
	@JsonProperty("mallOrderID")
	private UUID id;

	@NotNull
	@JsonProperty("user")
	private User user;

	@JsonProperty("mallName")
	private String mallName;

	@JsonProperty("mallID")
	private UUID mallID;

	@JsonProperty("orderDate")
	private OrderDate orderDate;

	@JsonProperty("ingredientsOFDishes")
	private List<String> ingredientsOFDishes = new ArrayList<>();

	public MallOrders() {
		super();
		if (this.id == null)
			this.id = Generators.timeBasedGenerator().generate();

		if (this.orderDate == null)
			this.orderDate = OrderDate.orderDate();
	}

	@PersistenceConstructor
	public MallOrders(@NotNull User user, String mallName, UUID mallID, List<String> ingredientsOFDishes) {
		super();
		this.id = Generators.timeBasedGenerator().generate();
		this.user = user;
		this.mallName = mallName;
		this.mallID = mallID;
		this.orderDate = OrderDate.orderDate();
		this.ingredientsOFDishes = ingredientsOFDishes;
	}

	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getMallName() {
		return mallName;
	}

	public void setMallName(String mallName) {
		this.mallName = mallName;
	}

	public UUID getMallID() {
		return mallID;
	}

	public void setMallID(UUID mallID) {
		this.mallID = mallID;
	}

	public OrderDate getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(OrderDate orderDate) {
		this.orderDate = orderDate;
	}

	public List<String> getIngredientsOFDishes() {
		return ingredientsOFDishes;
	}

	public void setIngredientsOFDishes(List<String> ingredientsOFDishes) {
		this.ingredientsOFDishes = ingredientsOFDishes;
	}

}
