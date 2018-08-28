package com.bigrestaurant.system.dishes.repo;

import org.springframework.data.mongodb.repository.MongoRepository;
import com.bigrestaurant.system.dishes.model.DeliverMan;

public interface DeliverManRepo extends MongoRepository<DeliverMan, Long> {

}
