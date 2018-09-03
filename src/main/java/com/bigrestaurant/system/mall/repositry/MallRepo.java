package com.bigrestaurant.system.mall.repositry;

import java.util.UUID;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.bigrestaurant.system.mall.model.Mall;

public interface MallRepo extends MongoRepository<Mall, UUID> {

}
