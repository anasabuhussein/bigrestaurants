package com.bigrestaurant.system.dishes.repo;

import java.util.UUID;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.bigrestaurant.system.dishes.model.RestaurantOrders;

public interface OrdersRepo extends MongoRepository<RestaurantOrders, UUID> {

}
