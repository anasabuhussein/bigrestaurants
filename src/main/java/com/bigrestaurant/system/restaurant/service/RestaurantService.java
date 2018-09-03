package com.bigrestaurant.system.restaurant.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import com.bigrestaurant.system.dishes.model.Dishes;
import com.bigrestaurant.system.operations.MongoOperations;
import com.bigrestaurant.system.repositories.FacadeRepositry;
import com.bigrestaurant.system.restaurant.modal.Restaurant;
import com.bigrestaurant.system.services.FacadeService;
import com.fasterxml.uuid.Generators;

@Service
public class RestaurantService implements MongoOperations<Restaurant> {

	@Autowired
	private FacadeRepositry facadeRepositry;

	@Autowired
	private FacadeService facadeService;

	@SuppressWarnings("unchecked")
	@Override
	public List<Restaurant> findAll() {
		List<Restaurant> restaurants = facadeRepositry.getRestaurantRepo().findAll();
		for (Restaurant restaurant : restaurants) {
			addDishesForRestoList(restaurant, (List<Dishes>) findAllByEmbeddedObject(restaurant.getRestName()));
		}

		return restaurants;
	}

	@SuppressWarnings("unchecked")
	@Override
	public <K> Restaurant findById(K object) {
		Optional<Restaurant> restaurant = facadeRepositry.getRestaurantRepo().findById((UUID) object);
		if (restaurant.isPresent()) {
			Restaurant resto = restaurant.get();
			return addDishesForRestoList(resto, ((List<Dishes>) findAllByEmbeddedObject(resto.getRestName())));
		}
		return null;
	}

	public Restaurant addDishesForRestoList(Restaurant restaurant, List<Dishes> dishes) {
		restaurant.setDishes(dishes);
		return restaurant;
	}

	@Override
	public Restaurant findByObject(Restaurant object) {
		return null;
	}

	@Override
	public Restaurant save(Restaurant object) {
		if (object != null)
			return facadeRepositry.getRestaurantRepo().save(object);
		return null;
	}

	@Override
	public <G> Restaurant update(Restaurant object, G g) {
		Restaurant restaurant = this.findById(object.getId());

		if (restaurant == null)
			return null;

		if (object.getRestName() != null)
			restaurant.setRestName(object.getRestName());

		if (object.getCity() != null)
			restaurant.setCity(object.getCity());

		if (object.getCountry() != null)
			restaurant.setCountry(object.getCountry());

		if (object.getLocation() != null)
			restaurant.setLocation(object.getLocation());

		if (object.getDishes() != null || !object.getDishes().isEmpty()) {

			// used just first time .
			if (restaurant.getDishes().size() == 0) {
				for (Dishes dishes : object.getDishes()) {
					dishes.setDishesID(Generators.timeBasedGenerator().generate());
					dishes.setRestaurantName(restaurant.getRestName());
					facadeService.getDishesService().save(dishes);
				}
			}

			for (Dishes dishes : restaurant.getDishes()) {
				for (Dishes dishes1 : object.getDishes()) {
					if (!dishes.getName().equals(dishes1.getName())) {
						dishes1.setRestaurantName(restaurant.getRestName());
						dishes1.setDishesID(Generators.timeBasedGenerator().generate());
						facadeService.getDishesService().save(dishes1);
					}

					if (dishes.getName().equals(dishes1.getName())) {
						facadeService.getDishesService().update(dishes1, dishes.getDishesID());
					}

				}
			}

		}

		return this.save(restaurant);

	}

	@Override
	public void delete(Restaurant object) {
		facadeRepositry.getRestaurantRepo().delete(object);
	}

	public void deleteDishes(Dishes object) {
		facadeService.getDishesService().delete(object);
	}

	/**
	 * find by restaurant name from restaurant class ...
	 **/
	@Override
	public <G> Restaurant findByEmbeddedObject(G m) {
		Query query = new Query();
		query.addCriteria(Criteria.where("restName").all(m));
		return facadeRepositry.getMongoTemplate().findOne(query, Restaurant.class);
	}

	/**
	 * find by all dishes of restaurant by name of resto. from dishes class ...
	 **/
	@Override
	public <G> List<?> findAllByEmbeddedObject(G m) {
		Query query = new Query();
		query.addCriteria(Criteria.where("restaurantName").all(m));
		return facadeRepositry.getMongoTemplate().find(query, Dishes.class);
	}

	@Override
	public boolean iterator(Restaurant t) {
		return false;
	}

}
