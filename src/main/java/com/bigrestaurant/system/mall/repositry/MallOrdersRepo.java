package com.bigrestaurant.system.mall.repositry;

import java.util.UUID;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.bigrestaurant.system.mall.model.MallOrders;

@Repository
public interface MallOrdersRepo extends MongoRepository<MallOrders, UUID> {

}
