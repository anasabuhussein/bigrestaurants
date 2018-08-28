package com.bigrestaurant.system.restaurant.repo;

import java.util.UUID;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.bigrestaurant.system.restaurant.modal.Restaurant;

@Repository
public interface RestaurantRepo extends MongoRepository<Restaurant, UUID> {
	public Restaurant findByRestName(String restname);
}
