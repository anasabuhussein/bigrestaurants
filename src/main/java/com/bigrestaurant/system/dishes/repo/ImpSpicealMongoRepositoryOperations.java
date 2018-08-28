package com.bigrestaurant.system.dishes.repo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;

@Component
public class ImpSpicealMongoRepositoryOperations<T> implements SpicealMongoRepositoryOperations<T> {

	private Class<T> type;

	@Autowired
	private MongoTemplate mongoTemplate;

	public ImpSpicealMongoRepositoryOperations() {
		super();
	}

	@Override
	public Query query() {
		return new Query();
	}

	@Override
	public <O> T findByEmbbdedObject(O object, String key) {
		query().addCriteria(Criteria.where(key).all(object));
		return mongoTemplate.findOne(query(), type);
	}

	@SuppressWarnings("unchecked")
	@Override
	public ImpSpicealMongoRepositoryOperations<?> setClass(Class<?> type) {
		this.type = (Class<T>) type;
		return this;
	}

}
