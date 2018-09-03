package com.bigrestaurant.system.dishes.controller;

import java.io.IOException;
import java.util.List;
import java.util.Set;
import java.util.UUID;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bigrestaurant.system.dishes.model.Dishes;
import com.bigrestaurant.system.dishes.model.server.message.ServerExciption;
import com.bigrestaurant.system.dishes.model.server.message.ServerMessage;
import com.bigrestaurant.system.services.FacadeService;
import com.bigrestaurant.system.view.View;
import com.fasterxml.jackson.annotation.JsonView;
import com.fasterxml.uuid.Generators;

/**
 * @author Anas Abu Hussein
 * @since 16/8/2018
 **/
@RestController
public class DishesController implements GeneralControllerPath {

	@Autowired
	private FacadeService services;

	/**
	 * **********************************************************************************************
	 * Get Methods ...
	 * **********************************************************************************************
	 **/

	/**
	 * get all dishes
	 **/
	@GetMapping(value = "/dishes", produces = { MediaType.APPLICATION_JSON_UTF8_VALUE,
			MediaType.APPLICATION_XML_VALUE })
	@JsonView(View.DishesView.class)
	public ResponseEntity<List<Dishes>> getDishes(
			@RequestParam(value = "prices", required = false, defaultValue = "") String price,
			@RequestParam(value = "rates", required = false, defaultValue = "") String rate) {

		// for prices request ...
		if ((!price.equals("")) && (rate.equals("")))
			return new ResponseEntity<>(services.getDishesService().findByPrice(Double.parseDouble(price.trim())),
					HttpStatus.OK);

		// for rate request ...
		if ((rate != null && !rate.equals("")) && (price.equals("")))
			return new ResponseEntity<>(services.getDishesService().findByRating(Long.parseLong(rate.trim())),
					HttpStatus.OK);

		// for price and rating ...
		if ((!price.equals("")) && (!rate.equals("")))
			return new ResponseEntity<>(services.getDishesService().findByPriceAndRating(
					Double.parseDouble(price.trim()), Long.parseLong(rate.trim())), HttpStatus.OK);

		// for general and error parameters request ...
		if ((price.equals("")) && (rate.equals("")))
			return new ResponseEntity<>(services.getDishesService().findAll(), HttpStatus.OK);

		return null;
	}

	/**
	 * get dishes By ID ...
	 **/
	@SuppressWarnings("unchecked")
	@GetMapping(value = { "/dishes/{id}", "/dishes/name/{name}" }, produces = { MediaType.APPLICATION_JSON_UTF8_VALUE,
			MediaType.APPLICATION_XML_VALUE })
	@JsonView(View.RestaurantView.class)
	public <T> ResponseEntity<T> getDishesByID(@PathVariable(required = false, value = "id") UUID id,
			@PathVariable(required = false, value = "name") String name) {
		// just for id with path variable ...

		try {

			Dishes dishes = null;

			if (id != null)
				dishes = services.getDishesService().findById(id);

			if (name != null)
				dishes = services.getDishesService().findByName(name);

			if (dishes == null) {
				throw new ServerExciption();
			}

			return (ResponseEntity<T>) new ResponseEntity<>(dishes, HttpStatus.OK);

		} catch (ServerExciption e) {
			return (ResponseEntity<T>) new ResponseEntity<>(
					new ServerMessage(HttpStatus.NOT_FOUND.value(), HttpStatus.NOT_FOUND.name(), e.getMessage()),
					HttpStatus.NOT_FOUND);
		}

	}

	/**
	 * get comments of dishes By ID ...
	 **/
	@GetMapping(value = "/dishes/{id}/comments", produces = { MediaType.APPLICATION_JSON_UTF8_VALUE,
			MediaType.APPLICATION_XML_VALUE })
	@JsonView(View.CommentsOfDishes.class)
	public ResponseEntity<Dishes> getCommentsOfDishesByID(@PathVariable(value = "id") UUID id) {
		// just for id with path variable ...
		return new ResponseEntity<>(services.getDishesService().findCommentsOfDishes(id), HttpStatus.OK);
	}

	/**
	 * **********************************************************************************************
	 * Post Methods ...
	 * **********************************************************************************************
	 **/

	/**
	 * post new dishes ...
	 **/
	@SuppressWarnings("unlikely-arg-type")
	@PostMapping(value = "/dishes", produces = { MediaType.APPLICATION_JSON_UTF8_VALUE,
			MediaType.APPLICATION_XML_VALUE }, consumes = { MediaType.APPLICATION_JSON_UTF8_VALUE,
					MediaType.APPLICATION_XML_VALUE })
	@Validated
	public ResponseEntity<ServerMessage> setDishes(@RequestBody Dishes dishes) {
		try {

			dishes.setDishesID(Generators.timeBasedGenerator().generate());

			ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
			Validator validator = factory.getValidator();
			Set<ConstraintViolation<Dishes>> violations = validator.validate(dishes);

			StringBuilder valid = new StringBuilder();
			for (ConstraintViolation<Dishes> violation : violations) {
				valid.append(violation.getPropertyPath() + " : " + violation.getMessage() + ", ");
			}

			if (services.getDishesService().iterator(dishes))
				throw new ServerExciption("collection have a doucument with same name...");

			if ((!valid.equals("") || !valid.equals(null)) && valid.length() > 2)
				throw new ServerExciption(valid.toString());

			services.getDishesService().save(dishes);
			return new ResponseEntity<>(
					new ServerMessage(HttpStatus.OK.value(), HttpStatus.OK.name(), "object is saved."), HttpStatus.OK);

		} catch (ServerExciption e) {
			return new ResponseEntity<>(
					new ServerMessage(HttpStatus.BAD_REQUEST.value(), HttpStatus.BAD_REQUEST.name(), e.getMessage()),
					HttpStatus.BAD_REQUEST);
		}
	}

	/**
	 * Post comments of dishes By ID ...
	 **/
	@PostMapping(value = "/dishes/{id}/comments", produces = { MediaType.APPLICATION_JSON_UTF8_VALUE,
			MediaType.APPLICATION_XML_VALUE }, consumes = { MediaType.APPLICATION_JSON_UTF8_VALUE,
					MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<ServerMessage> setCommentsOfDishesByID(@PathVariable(value = "id") UUID id,
			@RequestBody Dishes dishesBode) {

		try {

			Dishes dishes = services.getDishesService().findById(id);
			int sizeBefore = 0;
			int sizeAfter = 0;

			if (dishesBode.getComments().get(0).getComment() == null
					&& (dishesBode.getComments().get(0).getUser() == null))
				throw new ServerExciption("comments is empty must add comment att and user att.");

			if (dishes != null) {

				sizeBefore = dishes.getCountOfComments();
				dishes.getComments().add((dishesBode.getComments().get(0)));
				sizeAfter = dishes.getCountOfComments();

				services.getDishesService().save(dishes);
			}

			if (dishes == null)
				throw new ServerExciption("the object id not found ...");

			if (sizeAfter == sizeBefore)
				throw new ServerExciption("the object of comments not add yet ...");

			return new ResponseEntity<>(
					new ServerMessage(HttpStatus.OK.value(), HttpStatus.OK.name(), "Object is saved."), HttpStatus.OK);

		} catch (ServerExciption e) {
			return new ResponseEntity<>(
					new ServerMessage(HttpStatus.BAD_REQUEST.value(), HttpStatus.BAD_REQUEST.name(), e.getMessage()),
					HttpStatus.BAD_REQUEST);
		}
	}

	/**
	 * **********************************************************************************************
	 * Put Methods ...
	 * **********************************************************************************************
	 **/

	/**
	 * Put exist dishes with more info...
	 * 
	 * @throws IOException
	 **/
	@PutMapping(value = { "/dishes/{id}", "/dishes/{id}/comments", "/dishes/name/{name}" }, produces = {
			MediaType.APPLICATION_JSON_UTF8_VALUE, MediaType.APPLICATION_XML_VALUE }, consumes = {
					MediaType.APPLICATION_JSON_UTF8_VALUE, MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<ServerMessage> updatDishesWithMoreInfo(@PathVariable(value = "id", required = false) UUID id,
			@PathVariable(value = "name", required = false) String name, @RequestBody Dishes dishesBody) {

		try {

			if (id == null && name != null)
				id = services.getDishesService().findByName(name).getDishesID();

			if (services.getDishesService().update(dishesBody, id) != null)
				return new ResponseEntity<>(
						new ServerMessage(HttpStatus.OK.value(), HttpStatus.OK.name(), "object saved."), HttpStatus.OK);

			throw new Exception("not found dishes object in database.");
		} catch (Exception e) {

			return new ResponseEntity<>(
					new ServerMessage(HttpStatus.NOT_MODIFIED.value(), HttpStatus.NOT_MODIFIED.name(), "object saved."),
					HttpStatus.NOT_MODIFIED);
		}
	}

	/**
	 * **********************************************************************************************
	 * delete Methods ...
	 * **********************************************************************************************
	 **/

	/**
	 * delete exist dishes by id ...
	 * 
	 * @throws IOException
	 * @param id   UUID
	 * @param name String
	 * @return
	 */
	@DeleteMapping(value = { "/dishes/{id}", "/dishes/name/{name}" }, produces = {
			MediaType.APPLICATION_JSON_UTF8_VALUE, MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<ServerMessage> deleteDishesByID(@PathVariable(value = "id", required = false) UUID id,
			@PathVariable(required = false, value = "name") String name) {

		try {

			Dishes dishes = null;

			if (id != null)
				dishes = services.getDishesService().findById(id);

			if (name != null)
				dishes = services.getDishesService().findByName(name);

//			if (id == null || name == null)
//				throw new ServerExciption();

			services.getDishesService().delete(dishes);

			Dishes checkDishesExist = services.getDishesService().findById(id);
			if (checkDishesExist == null)
				return new ResponseEntity<>(
						new ServerMessage(HttpStatus.OK.value(), HttpStatus.OK.name(), "object deleted."),
						HttpStatus.OK);

			return new ResponseEntity<>(new ServerMessage(HttpStatus.NOT_ACCEPTABLE.value(),
					HttpStatus.NOT_ACCEPTABLE.name(), "object not deleted."), HttpStatus.NOT_ACCEPTABLE);

		} catch (Exception e) {
			return new ResponseEntity<>(
					new ServerMessage(HttpStatus.NOT_FOUND.value(), HttpStatus.NOT_FOUND.name(), e.getMessage()),
					HttpStatus.NOT_FOUND);
		}
	}

	/**
	 * delete comments for exist dishes by id ...
	 * 
	 * @throws IOException
	 * @param id   UUID
	 * @param name String
	 * @return
	 */
	@DeleteMapping(value = { "/dishes/{id}/comments" }, produces = { MediaType.APPLICATION_JSON_UTF8_VALUE,
			MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<ServerMessage> deleteCommentsFromDishes(@RequestBody Dishes dishes,
			@PathVariable(value = "id", required = false) UUID id) {

		try {

			if (dishes.getComments() != null)
				services.getDishesService().deleteComment(dishes, id);

			return new ResponseEntity<>(new ServerMessage(HttpStatus.NOT_ACCEPTABLE.value(),
					HttpStatus.NOT_ACCEPTABLE.name(), "object not deleted."), HttpStatus.NOT_ACCEPTABLE);

		} catch (Exception e) {
			return new ResponseEntity<>(
					new ServerMessage(HttpStatus.NOT_FOUND.value(), HttpStatus.NOT_FOUND.name(), e.getMessage()),
					HttpStatus.NOT_FOUND);
		}
	}

}
