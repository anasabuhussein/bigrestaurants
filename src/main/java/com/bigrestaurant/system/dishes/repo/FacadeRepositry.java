package com.bigrestaurant.system.dishes.repo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Component;

import com.bigrestaurant.system.restaurant.repo.RestaurantRepo;

@Component
public class FacadeRepositry {

	@Autowired
	private MongoTemplate mongoTemplate;

	@Autowired
	private UserRepo userRepo;

	@Autowired
	private DeliverManRepo deliverManRepo;

	@Autowired
	private DishesRepo dishesRepo;

	@Autowired
	private RestaurantRepo restaurantRepo;

	@Autowired
	private ImpSpicealMongoRepositoryOperations<?> ismro;

	public FacadeRepositry() {
		super();
	}

	public MongoTemplate getMongoTemplate() {
		return mongoTemplate;
	}

	public UserRepo getUserRepo() {
		return userRepo;
	}

	public DeliverManRepo getDeliverManRepo() {
		return deliverManRepo;
	}

	public DishesRepo getDishesRepo() {
		return dishesRepo;
	}

	public RestaurantRepo getRestaurantRepo() {
		return restaurantRepo;
	}

	public ImpSpicealMongoRepositoryOperations<?> getIsmro() {
		return ismro;
	}
}
