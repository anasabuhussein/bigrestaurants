package com.bigrestaurant.system.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bigrestaurant.system.mall.services.MallOrdersService;
import com.bigrestaurant.system.mall.services.MallService;
import com.bigrestaurant.system.restaurant.dishes.service.DishesService;
import com.bigrestaurant.system.restaurant.dishes.service.UserService;
import com.bigrestaurant.system.restaurant.service.RestaurantOredreService;
import com.bigrestaurant.system.restaurant.service.RestaurantService;

/**
 * <h1>Description</h1> <br>
 * - this class collect all services in system and provide it in one class.
 * 
 * @author Anas Abu Hussein
 * @since 28/8/2018
 **/
@Service
public class FacadeService {

	@Autowired
	private DishesService dishesService;

	@Autowired
	private RestaurantOredreService oredreService;

	@Autowired
	private UserService userService;

	@Autowired
	private RestaurantService restaurantService;

	@Autowired
	private MallService mallService;

	@Autowired
	private MallOrdersService mallOrdersService;

	public FacadeService() {
		super();
	}

	public DishesService getDishesService() {
		return dishesService;
	}

	public RestaurantOredreService getOredreService() {
		return oredreService;
	}

	public UserService getUserService() {
		return userService;
	}

	public RestaurantService getRestaurantService() {
		return restaurantService;
	}

	public MallService getMallService() {
		return mallService;
	}

	public MallOrdersService getMallOrdersService() {
		return mallOrdersService;
	}

}
