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

@Service
public class RestaurantService implements MongoOperations<Restaurant> {

	@Autowired
	private FacadeRepositry facadeRepositry;

	@Autowired
	private FacadeService facadeService;

	@Override
	public List<Restaurant> findAll() {
		return facadeRepositry.getRestaurantRepo().findAll();
	}

	@Override
	public <K> Restaurant findById(K object) {
		Optional<Restaurant> restaurant = facadeRepositry.getRestaurantRepo().findById((UUID) object);
		if (restaurant.isPresent())
			return restaurant.get();

		return null;
	}

	@Override
	public Restaurant findByObject(Restaurant object) {
		return null;
	}

	@Override
	public Restaurant save(Restaurant object) {
		if (object != null)
			facadeRepositry.getRestaurantRepo().save(object);
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

		if (object.getDishes() != null || !object.getDishes().isEmpty())
			for (Dishes dishes : restaurant.getDishes()) {
				for (Dishes dishes1 : object.getDishes()) {
					if (dishes.getDishesID() != dishes1.getDishesID() || dishes.getName() != dishes1.getName()
							|| !restaurant.getDishes().contains(dishes1))
						restaurant.getDishes().add(dishes1);

					if (dishes.getDishesID() == dishes1.getDishesID() || dishes.getName() == dishes1.getName()
							|| restaurant.getDishes().contains(dishes1))
						facadeService.getDishesService().update(dishes, dishes.getDishesID());
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

	@Override
	public <G> Restaurant findByEmbeddedObject(G m) {
		Query query = new Query();
		query.addCriteria(Criteria.where("restName").all(m));
		return facadeRepositry.getMongoTemplate().findOne(query, Restaurant.class);
	}

	@Override
	public <G> List<?> findAllByEmbeddedObject(G m) {
		Query query = new Query();
		query.addCriteria(Criteria.where("dishes.restaurantName").all(m));
		return facadeRepositry.getMongoTemplate().find(query, Dishes.class);
	}

	@Override
	public boolean iterator(Restaurant t) {
		return false;
	}

}
