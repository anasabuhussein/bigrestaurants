package com.bigrestaurant.system.restaurant.dishes.repo;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.bigrestaurant.system.restaurant.dishes.model.DeliverMan;

public interface DeliverManRepo extends MongoRepository<DeliverMan, Long> {

}
