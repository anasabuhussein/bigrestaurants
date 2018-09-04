package com.bigrestaurant.system.restaurant.dishes.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Service;

import com.bigrestaurant.system.operations.MongoOperations;
import com.bigrestaurant.system.repositories.FacadeRepositry;
import com.bigrestaurant.system.restaurant.dishes.model.Comments;
import com.bigrestaurant.system.restaurant.dishes.model.Dishes;

/**
 * @author anas abu hussein
 *
 */
@Service
@Order(1)
public class DishesService implements MongoOperations<Dishes> {

	@Autowired
	private FacadeRepositry facadeRepositry;

	private static final Logger log = LoggerFactory.getLogger(DishesService.class);

	@Override
	public List<Dishes> findAll() {
		log.info("# find all dishes.");
		return facadeRepositry.getDishesRepo().findAll();
	}

	@Override
	public <K> Dishes findById(K object) {

		Optional<Dishes> dishes = facadeRepositry.getDishesRepo().findById((UUID) object);
		if (dishes.isPresent()) {
			log.info("# find dishes by id : " + object);

			Dishes d2 = dishes.get();

			d2.setRestaurant(facadeRepositry.getRestaurantRepo().findByRestName(d2.getRestaurantName()));

			return d2;
		}
		return null;
	}

	public Dishes findByName(String name) {
		log.info("# find dishes by name : " + name);
		return getFacadeRepositry().getDishesRepo().findByName(name);
	}

	@Override
	public Dishes findByObject(Dishes object) {
		return null;
	}

	@Override
	public Dishes save(Dishes object) {
		log.info("# dishes saved with name : " + object.getName() + " with id :" + object.getDishesID());
		return facadeRepositry.getDishesRepo().save(object);
	}

	@SuppressWarnings("hiding")
	@Override
	public <UUID> Dishes update(Dishes dishes, UUID id) {

		Dishes original = this.findById(id);
		if (dishes.getDishesID() == null)
			dishes.setDishesID(original.getDishesID());

		if (dishes.getPic() != null)
			original.setPic(dishes.getPic());

		if (dishes.getPrice() != 0)
			original.setPrice(dishes.getPrice());

		if (dishes.getDescription() != null)
			original.setDescription(dishes.getDescription());

		if (dishes.getName() != null)
			original.setName(dishes.getName());

		if (dishes.isAvailableDelivery() != false)
			original.setAvailableDelivery(dishes.isAvailableDelivery());

		// update comments
		if (dishes.getComments() != null || !dishes.getComments().isEmpty())
			// get all comments from request .
			for (Comments updateComments : dishes.getComments()) {
				// search of user .
				int count = 0;
				for (Comments comments : original.getComments()) {
					// check if its exist comments and user id and index of comments.
					if ((updateComments.getUser().getId() == comments.getUser().getId())
							&& (updateComments.getComment() != comments.getComment())
							&& (updateComments.getIndex() == comments.getIndex())) {
						original.getComments().set(count, updateComments);
						log.info("# update comments for dishes with id : " + original.getDishesID());
					}
				}
			}

		// for check the main components ...
		if (dishes.getMainComponents() != null || !dishes.getMainComponents().isEmpty()) {

			// add new component ...
			if (dishes.getMainComponents().size() != original.getMainComponents().size()) {
				for (int i = 0; i < dishes.getMainComponents().size(); i++) {
					if (!original.getMainComponents().contains(dishes.getMainComponents().get(i)))
						original.getMainComponents().add(dishes.getMainComponents().get(i));
					log.info("# add new components for dishes with id : " + original.getDishesID());
				}
			}

			// update component by check the characters and update it...
			if (dishes.getMainComponents().size() == original.getMainComponents().size()) {

				// sorting component ...
				Collections.sort(dishes.getMainComponents());
				Collections.sort(original.getMainComponents());

				// for loop, for update component by char...
				for (int i = 0; i < dishes.getMainComponents().size(); i++) {

					// check chars with not same length of main comp ...
					if (dishes.getMainComponents().get(i).toCharArray().length != original.getMainComponents().get(i)
							.toCharArray().length) {
						original.getMainComponents().set(i, dishes.getMainComponents().get(i));
						log.info("# edit components for dishes with id : " + original.getDishesID());
					}

					// check chars with same length of main comp ...
					if (dishes.getMainComponents().get(i).toCharArray().length == original.getMainComponents().get(i)
							.toCharArray().length) {
						for (int c = 0; c < dishes.getMainComponents().get(i).length(); c++) {
							char char1 = original.getMainComponents().get(i).charAt(c);
							char char2 = dishes.getMainComponents().get(i).charAt(c);

							if (char2 != char1) {
								original.getMainComponents().set(i,
										original.getMainComponents().get(i).replace(char1, char2));
								log.info("# edit components for dishes with id : " + original.getDishesID());
							}
						}
					}
				}
			}

		}

		log.info("# add new components for dishes with id : " + original.getDishesID());
		return this.save(original);
	}

	@Override
	public void delete(Dishes object) {
		log.info("# delete dishes with id : " + object.getDishesID());
		facadeRepositry.getDishesRepo().delete(object);
	}

	/**
	 * delete comments.
	 * 
	 * @param dishes Dishes
	 * @param id     UUID
	 */
	public void deleteComment(Dishes dishes, UUID id) {
		log.info("# delete comments of dishes with id : " + dishes.getDishesID());

		Dishes originalDishes = this.findById(id);
		for (int i = 0; i < originalDishes.getComments().size(); i++) {
			for (int j = 0; j < dishes.getComments().size(); j++) {
				if (dishes.getComments().get(j).getIndex() == originalDishes.getComments().get(i).getIndex())
					originalDishes.getComments().remove(i);
			}
		}

		facadeRepositry.getDishesRepo().save(originalDishes);

	}

	@Override
	public <G> Dishes findByEmbeddedObject(G m) {
		return null;
	}

	@Override
	public <G> List<Dishes> findAllByEmbeddedObject(G m) {
		return null;
	}

	/**
	 * find comments of dishes
	 **/
	public Dishes findCommentsOfDishes(UUID id) {
		log.info("# find by id of dishes for id : " + id);
		return this.findById(id);
	}

	/**
	 * find comments of dishes
	 **/
	public List<Dishes> findByPrice(double price) {
		log.info("# find by price of dishes with price : " + price);
		return facadeRepositry.getDishesRepo().findByPrice(price);
	}

	/**
	 * find by rating
	 **/
	public List<Dishes> findByRating(long rating) {

		List<Dishes> dishes = facadeRepositry.getDishesRepo().findAll();
		Iterator<Dishes> iterator = dishes.iterator();

		List<Dishes> dishesWithRating = new ArrayList<>();

		while (iterator.hasNext()) {
			Dishes dishes2 = iterator.next();
			if (dishes2.getRating() != null && rating == dishes2.getRating().getRating()) {
				dishesWithRating.add(dishes2);
			}
		}

		log.info("# find by rate of dishes with rate : " + rating);
		return dishesWithRating;

	}

	/**
	 * find by rating and price
	 **/
	public List<Dishes> findByPriceAndRating(double price, long rating) {
		List<Dishes> prices = findByPrice(price);
		List<Dishes> listDishes = new ArrayList<>();

		Iterator<Dishes> generalIter = prices.iterator();
		while (generalIter.hasNext()) {

			Dishes dishes = generalIter.next();
			if (dishes.getPrice() == price)
				if (dishes.getRating() != null && dishes.getRating().getRating() == rating)
					listDishes.add(dishes);

		}

		log.info("# find by price and rating of dishes with rate and price : " + rating + "," + price);
		return listDishes;
	}

	// iterator for check name of dishes
	@Override
	public boolean iterator(Dishes _dishes) {
		boolean check = false;
		Iterator<Dishes> iterator = this.findAll().iterator();

		while (iterator.hasNext()) {
			Dishes dishes = iterator.next();

			if (_dishes.getName().equals(dishes.getName())) {
				check = true;
				log.info("# find by other dishes with same name : " + dishes.getName());
				break;
			}
		}
		return check;
	}

	public FacadeRepositry getFacadeRepositry() {
		log.info("# from facadeRepositry. ");
		return facadeRepositry;
	}

}
