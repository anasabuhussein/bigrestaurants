package com.bigrestaurant.system.restaurant.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import com.bigrestaurant.system.model.OrderDate;
import com.bigrestaurant.system.operations.MongoOperations;
import com.bigrestaurant.system.repositories.FacadeRepositry;
import com.bigrestaurant.system.restaurant.dishes.model.Dishes;
import com.bigrestaurant.system.restaurant.modal.Restaurant;
import com.bigrestaurant.system.restaurant.modal.RestaurantOrders;

@Service
@Order(3)
public class RestaurantOredreService implements CommandLineRunner, MongoOperations<RestaurantOrders> {

	@Autowired
	private FacadeRepositry facadeRepositry;

	@Override
	public List<RestaurantOrders> findAll() {
		return facadeRepositry.getOrdersOfDishesRepo().findAll();
	}

	@Override
	public <K> RestaurantOrders findById(K object) {
		Optional<RestaurantOrders> orders = facadeRepositry.getOrdersOfDishesRepo().findById((UUID) object);
		if (orders.isPresent())
			return orders.get();

		return null;
	}

	@Override
	public RestaurantOrders findByObject(RestaurantOrders object) {
		return null;
	}

	@Override
	public RestaurantOrders save(RestaurantOrders object) {
		return facadeRepositry.getOrdersOfDishesRepo().save(object);
	}

	public RestaurantOrders saveWithName(RestaurantOrders object, String name) {
		if (name != null)
			for (Dishes dishes : object.getDishes()) {
				dishes.setRestaurantName(name);
			}
		return this.save(object);
	}

	@Override
	public <G> RestaurantOrders update(RestaurantOrders object, G g) {

		RestaurantOrders orders = this.findById(g);

		if (object.getUser() != null)
			orders.setUser(object.getUser());

		if (object.getDishes() != null || !object.getDishes().isEmpty())
			orders.setDishes(object.getDishes());

		if (object.isApproval() == false || object.isApproval() == true)
			orders.setApproval(object.isApproval());

		if (object.isDelever() == false || object.isDelever() == true)
			orders.setDelever(object.isDelever());

		if (object.isDone() == false || object.isDone() == true)
			orders.setDone(object.isDone());

		if (object.getUserRating() != 0)
			orders.setUserRating(object.getUserRating());

		if (object.getDate() != null)
			orders.setDate(object.getDate());

		return this.save(orders);
	}

	@Override
	public void delete(RestaurantOrders object) {
		facadeRepositry.getOrdersOfDishesRepo().delete(object);
	}

	@Override
	public <G> RestaurantOrders findByEmbeddedObject(G m) {
		return null;
	}

	@Override
	public <G> List<RestaurantOrders> findAllByEmbeddedObject(G name) {

		// get restaurant from database by name.
		Restaurant restaurant = facadeRepositry.getRestaurantRepo().findByRestName((String) name);
		if (restaurant != null) {
			Query query = new Query();
			query.addCriteria(Criteria.where("dishes.restaurantName").all(restaurant.getRestName()));
			return facadeRepositry.getMongoTemplate().find(query, RestaurantOrders.class);
		}

		return null;
	}

	@Override
	public boolean iterator(RestaurantOrders t) {
		return false;
	}

	public String restName(String name) {
		return name;
	}

	@Override
	public void run(String... args) throws Exception {

		ScheduledExecutorService executorService;
		executorService = Executors.newSingleThreadScheduledExecutor();
		executorService.scheduleAtFixedRate(new SchedualedDeleteOrdersWeeklyRunnable(findAll()), 0, 7, TimeUnit.DAYS);
	}

	public class SchedualedDeleteOrdersWeeklyRunnable implements Runnable {

		private List<RestaurantOrders> ordersList;

		public SchedualedDeleteOrdersWeeklyRunnable(List<RestaurantOrders> orders) {
			this.ordersList = orders;
		}

		@Override
		public void run() {
			// check all date for all orders
			RestaurantOrders orders = null;
			List<RestaurantOrders> ordersInWeekDate = new ArrayList<>();

			for (int i = 0; i < ordersList.size(); i++) {
				orders = ordersList.get(i);
				this.cachOrderDateAndMakeCompearsion(orders, ordersInWeekDate);
			}

			this.deleteOrdersListForLastWeek(ordersInWeekDate);

		}

		public void cachOrderDateAndMakeCompearsion(RestaurantOrders orders, List<RestaurantOrders> ordersInWeekDate) {
			// for date
			OrderDate orderDate = orders.getDate();
			LocalDate dateOfOrder = LocalDate.parse(orderDate.getHistory());
			LocalDate now = LocalDate.now();

			// calculate for week
			LocalDate dayInLastWeek = now.minusDays(7);
			if (dayInLastWeek.getDayOfWeek().getValue() == dateOfOrder.getDayOfWeek().getValue())
				ordersInWeekDate.add(orders);
		}

		// for delete orders in list added
		public void deleteOrdersListForLastWeek(List<RestaurantOrders> orders) {
			for (RestaurantOrders object : orders)
				RestaurantOredreService.this.delete(object);
		}
	}

}
