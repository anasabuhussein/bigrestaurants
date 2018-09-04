package com.bigrestaurant.system.restaurant.dishes.repo;

import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.bigrestaurant.system.restaurant.dishes.model.User;

@Repository
public interface UserRepo extends MongoRepository<User, String> {
	
	public Optional<User> findById(String object);
	
	public List<User> findByName(String name);
}
