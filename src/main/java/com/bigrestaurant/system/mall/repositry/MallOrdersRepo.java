package com.bigrestaurant.system.mall.repositry;

import java.util.UUID;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.bigrestaurant.system.mall.model.MallOrders;

public interface MallOrdersRepo extends MongoRepository<MallOrders, UUID> {

}
