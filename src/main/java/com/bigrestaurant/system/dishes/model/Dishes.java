package com.bigrestaurant.system.dishes.model;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.PersistenceConstructor;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import com.bigrestaurant.system.dishes.view.View;
import com.bigrestaurant.system.restaurant.modal.Restaurant;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonView;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import com.fasterxml.uuid.Generators;

/**
 * <h3>Description</h3> <br>
 * The restaurant have many of Dishes with attributes such as:
 * 
 * <ul>
 * <li>_id : unique.</li>
 * <li>name : name of dishes.</li>
 * <li>rating : total rating that coming from user his get like from 10.</li>
 * <li>pic : set dishes picture by restaurant.</li>
 * <li>main component list : the restaurant describe dishes receipt.</li>
 * <li>additional component list : the user add another dishes receipt.</li>
 * </ul>
 * 
 * @author Anas Abu-Hussein
 * @since 12/8/2018
 **/

@Document(collection = "dishes")
@JsonPropertyOrder({ "dishesID", "name", "components" })
@JsonInclude(Include.NON_EMPTY)

@JacksonXmlRootElement
public class Dishes implements GeneralModel {

	@Id
	@Field("dishesID")
	@JsonProperty(value = "dishesID")
	@JsonView(View.DishesView.class)
	private UUID dishesID;

	@NotNull(message = "test")
	@NotBlank(message = "test")
	@Field("dishesName")
	@JsonProperty(value = "dishesName")
	@JsonView(View.DishesView.class)
	private String name;

	@Field("rating")
	@JsonProperty(value = "rating")
	@JsonView(View.DishesView.class)
	private RatingOperations rating;

	@NotBlank(message = "test")
	@NotNull(message = "test")
	@Field("pic")
	@JsonProperty(value = "pic")
	@JsonView(View.DishesView.class)
	private String pic;

	@Field("description")
	@JsonProperty("description")
	@JsonView(View.DishesView.class)
	private String description;

	@Field("availableDelivery")
	@JsonProperty("availableDelivery")
	@JsonView(View.DishesView.class)
	private boolean availableDelivery;

	@Positive
	@Field(value = "price")
	@JsonProperty("price")
	@JsonView(View.DishesView.class)
	private double price;
	
	@NotNull
	@NotEmpty
	@Indexed
	@Field(value = "restaurantName")
	@JsonProperty("restaurantName")
	@JsonView(View.MainComponent.class)
	private String restaurantName;

	@Transient
	@JsonProperty(value = "restaurant")
	@JsonView(View.RestaurantView.class)
	private Restaurant restaurant;

	@Field("mainComponents")
	@NotEmpty(message = "test")
	@JsonProperty(value = "mainComponents")
	@JsonView(View.MainComponent.class)
	private List<String> mainComponents = new ArrayList<>();

	@Field("additonalComponent")
	@JsonProperty("additonalComponent")
	private List<String> additonalComponent = new ArrayList<>();

	@Field("comments")
	@JsonProperty("comments")
	@JsonView(View.CommentsOfDishes.class)
	private List<Comments> comments = new ArrayList<>();

	public Dishes() {
		super();
	}

	@PersistenceConstructor
	public Dishes(@NotNull @NotBlank String name, String pic, @NotEmpty List<String> mainComponents) {
		super();
		this.dishesID = Generators.timeBasedGenerator().generate();
		this.name = name;
		this.pic = pic;
		this.mainComponents = mainComponents;
	}

	@PersistenceConstructor
	public Dishes(@NotNull @NotBlank String name, RatingOperations rating, String pic,
			@NotEmpty List<String> mainComponents) {
		super();
		this.dishesID = Generators.timeBasedGenerator().generate();
		this.name = name;
		this.rating = rating;
		this.pic = pic;
		this.mainComponents = mainComponents;
	}

	@PersistenceConstructor
	public Dishes(@NotNull @NotBlank String name, RatingOperations rating, String pic,
			@NotEmpty List<String> mainComponents, List<String> additonalComponent) {
		super();
		this.dishesID = Generators.timeBasedGenerator().generate();
		this.name = name;
		this.rating = rating;
		this.pic = pic;
		this.mainComponents = mainComponents;
		this.additonalComponent = additonalComponent;
	}

	@PersistenceConstructor
	public Dishes(@NotNull(message = "test") @NotBlank(message = "test") String name, RatingOperations rating,
			@NotBlank(message = "test") @NotNull(message = "test") String pic, @NotNull @NotEmpty String restaurantName,
			String description, boolean availableDelivery, @Positive double price,
			@NotEmpty(message = "test") List<String> mainComponents, List<String> additonalComponent,
			List<Comments> comments) {
		super();
		this.dishesID = Generators.timeBasedGenerator().generate();
		this.name = name;
		this.rating = rating;
		this.pic = pic;
		this.restaurantName = restaurantName;
		this.description = description;
		this.availableDelivery = availableDelivery;
		this.price = price;
		this.mainComponents = mainComponents;
		this.additonalComponent = additonalComponent;
		this.comments = comments;
	}

	public UUID getDishesID() {
		return dishesID;
	}

	public void setDishesID(UUID dishesID) {
		this.dishesID = dishesID;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public RatingOperations getRating() {
		return rating;
	}

	public void setRating(RatingOperations rating) {
		this.rating = rating;
	}

	public String getPic() {
		return pic;
	}

	public void setPic(String pic) {
		this.pic = pic;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public boolean isAvailableDelivery() {
		return availableDelivery;
	}

	public void setAvailableDelivery(boolean availableDelivery) {
		this.availableDelivery = availableDelivery;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public List<String> getMainComponents() {
		return mainComponents;
	}

	public void setMainComponents(List<String> mainComponents) {
		this.mainComponents = mainComponents;
	}

	public List<String> getAdditonalComponent() {
		return additonalComponent;
	}

	public void setAdditonalComponent(List<String> additonalComponent) {
		this.additonalComponent = additonalComponent;
	}

	public List<Comments> getComments() {
		for (int i = 0; i < comments.size(); i++)
			comments.get(i).setIndex(i);
		return comments;
	}

	public void setComments(List<Comments> comments) {
		this.comments = comments;
	}

	public String getRestaurantName() {
		return restaurantName;
	}

	public void setRestaurantName(String restaurantName) {
		this.restaurantName = restaurantName;
	}

	public Restaurant getRestaurant() {
		return restaurant;
	}

	public void setRestaurant(Restaurant restaurant) {
		this.restaurant = restaurant;
	}

	@JsonProperty("links")
	@JsonView(View.DishesView.class)
	@Transient
	@Override
	public List<HATEOAS> getHATEOAS() {
		List<HATEOAS> links = new ArrayList<>();
		String generalPath = "/api/v1/";

		// get dishes by id ...
		links.add(new HATEOAS(generalPath + "dishes/" + getDishesID(), "self"));

		// get dishes by name ...
		links.add(new HATEOAS(generalPath + "dishes/name/" + getName(), "self"));

		// get comments of dishes by id ...
		links.add(new HATEOAS(generalPath + "dishes/" + getDishesID() + "/comments", "comments"));

		return links;
	}

	@JsonProperty("countOfComments")
	@JsonView(View.DishesView.class)
	@Transient
	public int getCountOfComments() {
		if (comments != null)
			if (!comments.isEmpty())
				return comments.size();
		return 0;
	}

}
