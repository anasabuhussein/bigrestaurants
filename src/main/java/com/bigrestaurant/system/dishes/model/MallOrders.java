package com.bigrestaurant.system.dishes.model;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.validation.constraints.NotNull;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.PersistenceConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

import com.bigrestaurant.system.model.OrderDate;
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

	@JsonProperty("orderDate")
	private OrderDate orderDate;

	@JsonProperty("ingredientsOFDishes")
	private List<String> ingredientsOFDishes = new ArrayList<>();

	public MallOrders() {
		super();
		if (this.id == null)
			this.id = Generators.timeBasedGenerator().generate();
	}

	@PersistenceConstructor
	public MallOrders(@NotNull User user, String mallName, List<String> ingredientsOFDishes) {
		super();
		this.id = Generators.timeBasedGenerator().generate();
		this.user = user;
		this.mallName = mallName;
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
