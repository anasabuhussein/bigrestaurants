package com.bigrestaurant.system.dishes.repo;

import java.util.List;
import java.util.UUID;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.bigrestaurant.system.dishes.model.Dishes;
import com.bigrestaurant.system.dishes.model.RatingOperations;

@Repository
public interface DishesRepo extends MongoRepository<Dishes, UUID> {

	public List<Dishes> findByPrice(double price);

	public List<Dishes> findByPic(String pic);

	public List<Dishes> findByRating(RatingOperations rating);

	public Dishes findByName(String name);
}
