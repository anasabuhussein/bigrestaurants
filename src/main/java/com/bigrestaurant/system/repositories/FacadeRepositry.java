package com.bigrestaurant.system.repositories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Component;

import com.bigrestaurant.system.mall.repositry.MallOrdersRepo;
import com.bigrestaurant.system.mall.repositry.MallRepo;
import com.bigrestaurant.system.restaurant.dishes.repo.DeliverManRepo;
import com.bigrestaurant.system.restaurant.dishes.repo.DishesRepo;
import com.bigrestaurant.system.restaurant.dishes.repo.ImpSpicealMongoRepositoryOperations;
import com.bigrestaurant.system.restaurant.dishes.repo.UserRepo;
import com.bigrestaurant.system.restaurant.repo.RestaurantOrdersRepo;
import com.bigrestaurant.system.restaurant.repo.RestaurantRepo;

/**
 * <h1>Description</h1> <br>
 * - this class collect all repositories in system and provide it in one class.
 * 
 * @author Anas Abu Hussein
 * @since 28/8/2018
 **/

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
	private RestaurantOrdersRepo ordersOfDishesRepo;

	@Autowired
	private RestaurantRepo restaurantRepo;

	@Autowired
	private MallRepo mallRepo;

	@Autowired
	private MallOrdersRepo mallOrdersRepo;

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

	public RestaurantOrdersRepo getOrdersOfDishesRepo() {
		return ordersOfDishesRepo;
	}

	public RestaurantRepo getRestaurantRepo() {
		return restaurantRepo;
	}

	public MallRepo getMallRepo() {
		return mallRepo;
	}

	public MallOrdersRepo getMallOrdersRepo() {
		return mallOrdersRepo;
	}

	public ImpSpicealMongoRepositoryOperations<?> getIsmro() {
		return ismro;
	}
}
