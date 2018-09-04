package com.bigrestaurant.system.operations;

import java.util.List;

/**
 * - this class implements General Operation of MongoRepository <br>
 * with generic type ... T => returns object from database, <br>
 * and G => GeneralModel Class to make polymorphism with query objects
 * 
 * @author Anas Abu Hussein
 * @since 14/8/2018
 */
public interface MongoOperations<T> {

	/**
	 * Get list of object
	 * 
	 * @return List of objects.
	 **/
	public List<T> findAll();

	/**
	 * Get object by id.
	 * 
	 * @return object
	 **/
	public <K> T findById(K object);

	/**
	 * Get specific object
	 * 
	 * @return Object
	 * @see com.bigrestaurant.system.restaurant.modal.RestaurantOrders.restaurant.model.Orders
	 * @see com.api.restaurant.model.Comments
	 * @see com.api.restaurant.model.User
	 * @see com.api.restaurant.model.Dishes
	 * @see com.api.restaurant.model.DeliverMan
	 * 
	 **/
	public T findByObject(T object);

	/**
	 * save object
	 * 
	 * @param object
	 **/
	public T save(T object);

	/**
	 * update object
	 * 
	 * @param object
	 **/
	public <G> T update(T object, G g );

	/**
	 * delete object
	 * 
	 * @param object
	 **/
	public void delete(T object);

	/**
	 * Get specific embedded object
	 * 
	 * @return Object inside model.*
	 * @see com.bigrestaurant.system.restaurant.modal.RestaurantOrders.restaurant.model.Orders
	 * @see com.api.restaurant.model.Comments
	 * @see com.api.restaurant.model.User
	 * @see com.api.restaurant.model.Dishes
	 * @see com.api.restaurant.model.DeliverMan
	 * 
	 **/
	public<G> T findByEmbeddedObject(G m);

	/**
	 * Get all by specific embedded object
	 * 
	 * @return Object inside model.*
	 * @see com.bigrestaurant.system.restaurant.modal.RestaurantOrders.restaurant.model.Orders
	 * @see com.api.restaurant.model.Comments
	 * @see com.api.restaurant.model.User
	 * @see com.api.restaurant.model.Dishes
	 * @see com.api.restaurant.model.DeliverMan
	 * 
	 **/
	public <G> List<?> findAllByEmbeddedObject(G m);
	
	public boolean iterator(T t) ;
	
}
