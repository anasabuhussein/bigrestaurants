package com.bigrestaurant.system.restaurant.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bigrestaurant.system.dishes.model.Dishes;
import com.bigrestaurant.system.operations.MongoOperations;
import com.bigrestaurant.system.restaurant.modal.Restaurant;
import com.bigrestaurant.system.restaurant.repo.RestaurantRepo;

@Service
public class RestaurantService implements MongoOperations<Restaurant> {
	
	@Autowired
	private RestaurantRepo restaurantRepo;
	
//	@Override
//	public void run(String... args) throws Exception {
//		// TODO Auto-generated method stub
//		Restaurant r = new Restaurant("sultan", "jordan", "amman", new GeoJsonPoint(20.55545, 5.4545));
//		restaurantRepo.save(r);
//	}
	
	@Override
	public List<Restaurant> findAll() {
		return restaurantRepo.findAll();
	}

	@Override
	public <K> Restaurant findById(K object) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Restaurant findByObject(Restaurant object) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Restaurant save(Restaurant object) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <G> Restaurant update(Restaurant object, G g) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(Restaurant object) {
		// TODO Auto-generated method stub

	}

	@Override
	public <G> Restaurant findByEmbeddedObject(G m) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <G> List<Restaurant> findAllByEmbeddedObject(G m) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean iterator(Restaurant t) {
		// TODO Auto-generated method stub
		return false;
	}

}
