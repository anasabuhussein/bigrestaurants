package com.bigrestaurant.system.dishes.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import com.bigrestaurant.system.dishes.model.Orders;
import com.bigrestaurant.system.operations.MongoOperations;
import com.bigrestaurant.system.repositories.FacadeRepositry;
import com.bigrestaurant.system.restaurant.modal.Restaurant;

@Service
@Order(3)
public class OredreService implements MongoOperations<Orders> {

	@Autowired
	private FacadeRepositry facadeRepositry;

	@Override
	public List<Orders> findAll() {
		return facadeRepositry.getOrdersOfDishesRepo().findAll();
	}

	@Override
	public <K> Orders findById(K object) {
		Optional<Orders> orders = facadeRepositry.getOrdersOfDishesRepo().findById((UUID) object);
		if (orders.isPresent())
			return orders.get();

		return null;
	}

	@Override
	public Orders findByObject(Orders object) {
		return null;
	}

	@Override
	public Orders save(Orders object) {
		return facadeRepositry.getOrdersOfDishesRepo().save(object);
	}

	@Override
	public <G> Orders update(Orders object, G g) {
		return null;
	}

	@Override
	public void delete(Orders object) {
		facadeRepositry.getOrdersOfDishesRepo().delete(object);
	}

	@Override
	public <G> Orders findByEmbeddedObject(G m) {
		return null;
	}

	@Override
	public <G> List<Orders> findAllByEmbeddedObject(G name) {

		// get restaurant from database by name.
		Restaurant restaurant = facadeRepositry.getRestaurantRepo().findByRestName((String) name);
		if (restaurant != null) {
			Query query = new Query();
			query.addCriteria(Criteria.where("dishes.restaurantName").all(restaurant.getRestName()));
			return facadeRepositry.getMongoTemplate().find(query, Orders.class);
		}

		return null;
	}

	@Override
	public boolean iterator(Orders t) {
		return false;
	}

}
