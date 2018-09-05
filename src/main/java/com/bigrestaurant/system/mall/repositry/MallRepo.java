package com.bigrestaurant.system.mall.repositry;

import java.util.UUID;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.bigrestaurant.system.mall.model.Mall;

@Repository
public interface MallRepo extends MongoRepository<Mall, UUID> {

}
