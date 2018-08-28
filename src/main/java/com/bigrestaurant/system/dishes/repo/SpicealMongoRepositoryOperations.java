package com.bigrestaurant.system.dishes.repo;

import org.springframework.data.mongodb.core.query.Query;

public interface SpicealMongoRepositoryOperations<T> {

	public <O> T findByEmbbdedObject(O object, String key);

	public ImpSpicealMongoRepositoryOperations<?> setClass(Class<?> type);

	public Query query();

}
