package com.bigrestaurant.system.restaurant.modal;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.bigrestaurant.system.services.FacadeService;

/**
 * Unused class! 
 **/
public class OrdersOfRestaurant {

	@Autowired
	private FacadeService facadeService;

	public List<RestaurantOrders> getOrdersOfRest(String name) {
		return facadeService.getOredreService().findAllByEmbeddedObject(name);
	}
}
