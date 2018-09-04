package com.bigrestaurant.system.restaurant.repo;

import java.util.UUID;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.bigrestaurant.system.restaurant.modal.RestaurantOrders;

public interface RestaurantOrdersRepo extends MongoRepository<RestaurantOrders, UUID> {

}
