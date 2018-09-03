package com.bigrestaurant.system.restaurant.controllers;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.bigrestaurant.system.dishes.controller.GeneralControllerPath;
import com.bigrestaurant.system.dishes.model.Dishes;
import com.bigrestaurant.system.dishes.model.server.message.ServerExciption;
import com.bigrestaurant.system.dishes.model.server.message.ServerMessage;
import com.bigrestaurant.system.restaurant.modal.Restaurant;
import com.bigrestaurant.system.services.FacadeService;

@RestController
public class RestaurantController implements GeneralControllerPath {

	@Autowired
	private FacadeService facadeService;

	/**
	 * Get method. ...
	 **/
	@GetMapping(value = "/restaurants", produces = { MediaType.APPLICATION_JSON_UTF8_VALUE,
			MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<?> findAll() {
		return new ResponseEntity<>(facadeService.getRestaurantService().findAll(), HttpStatus.OK);
	}

	@GetMapping(value = "/restaurants/{id}", produces = { MediaType.APPLICATION_JSON_UTF8_VALUE,
			MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<?> findById(@PathVariable UUID id) {
		return new ResponseEntity<>(facadeService.getRestaurantService().findById(id), HttpStatus.OK);
	}

	@GetMapping(value = "/restaurants/name/{name}", produces = { MediaType.APPLICATION_JSON_UTF8_VALUE,
			MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<?> findByName(@PathVariable String name) {
		return new ResponseEntity<>(facadeService.getRestaurantService().findByEmbeddedObject(name), HttpStatus.OK);
	}

	@GetMapping(value = { "/restaurants/{id}/dishes", "/restaurants/name/{name}/dishes" }, produces = {
			MediaType.APPLICATION_JSON_UTF8_VALUE, MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<?> findRestoDishes(@PathVariable(required = false, value = "") UUID id,
			@PathVariable(required = false, value = "") String name) {

		try {
			if ((name != null || name != "") && id == null)
				return new ResponseEntity<>(facadeService.getRestaurantService().findAllByEmbeddedObject(name),
						HttpStatus.OK);

			if (id != null && (name == null || name == "")) {
				Restaurant restaurant = facadeService.getRestaurantService().findById(id);
				return new ResponseEntity<>(
						facadeService.getRestaurantService().findAllByEmbeddedObject(restaurant.getRestName()),
						HttpStatus.OK);
			}

			throw new ServerExciption("the dishes not exist");
		} catch (Exception e) {
			return new ResponseEntity<>(
					new ServerMessage(HttpStatus.BAD_REQUEST.value(), HttpStatus.BAD_REQUEST.name(), e.getMessage()),
					HttpStatus.BAD_REQUEST);
		}

	}

	/**
	 * Post method. ...
	 **/
	@PostMapping(value = "/restaurants", produces = { MediaType.APPLICATION_JSON_UTF8_VALUE,
			MediaType.APPLICATION_XML_VALUE }, consumes = { MediaType.APPLICATION_JSON_UTF8_VALUE,
					MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<?> addNewRestaurantRepo(@RequestBody Restaurant restaurant) {
		if (facadeService.getRestaurantService().save(restaurant) != null)
			return new ResponseEntity<>(new ServerMessage(HttpStatus.CREATED.value(), HttpStatus.CREATED.name(),
					"the object are created and saved."), HttpStatus.CREATED);

		return new ResponseEntity<>(new ServerMessage(HttpStatus.NOT_ACCEPTABLE.value(),
				HttpStatus.NOT_ACCEPTABLE.name(), "the object dose not accepted."), HttpStatus.NOT_ACCEPTABLE);
	}

	/**
	 * Put method. ...
	 **/
	@PutMapping(value = { "/restaurants/{id}",
			"/restaurants/{id}/dishes/{dishesid}" }, produces = { MediaType.APPLICATION_JSON_UTF8_VALUE,
					MediaType.APPLICATION_XML_VALUE }, consumes = { MediaType.APPLICATION_JSON_UTF8_VALUE,
							MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<?> updateResto(@PathVariable(required = false, value = "") UUID id,
			@PathVariable(required = false, value = "") UUID dishesid, @RequestBody Restaurant reqRestaurant) {

		try {

//			Restaurant restaurant = null;
//
//			if (id != null && (name == null || name == ""))
//				restaurant = facadeService.getRestaurantService().findById(id);
//
//			if (id == null && (name != null || name != ""))
//				restaurant = facadeService.getRestaurantService().findByEmbeddedObject(name);
//
//			if (restaurant != null) {
			reqRestaurant.setId(id);
			if (facadeService.getRestaurantService().update(reqRestaurant, null) != null)
				return new ResponseEntity<>(new ServerMessage(HttpStatus.OK.value(), HttpStatus.OK.name(),
						"the object are update and saved."), HttpStatus.OK);
//			}

			throw new ServerExciption("the dishes not exist");
		} catch (Exception e) {
			return new ResponseEntity<>(
					new ServerMessage(HttpStatus.NOT_FOUND.value(), HttpStatus.NOT_FOUND.name(), e.getMessage()),
					HttpStatus.NOT_FOUND);
		}
	}

	/**
	 * Delete method. ...
	 **/
	@DeleteMapping(value = { "/restaurants/{id}", "/restaurants/name/{name}" }, produces = {
			MediaType.APPLICATION_JSON_UTF8_VALUE, MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<?> deleteResto(@PathVariable(required = false, value = "") UUID id,
			@PathVariable(required = false, value = "") String name) {

		try {

			Restaurant restaurant = null;

			if (id != null && (name == null || name == "")) {
				restaurant = facadeService.getRestaurantService().findById(id);
			}

			if (id == null && (name != null || name != "")) {
				restaurant = facadeService.getRestaurantService().findByEmbeddedObject(name);
			}

			if (restaurant != null) {
				facadeService.getRestaurantService().delete(restaurant);
				return new ResponseEntity<>(
						new ServerMessage(HttpStatus.OK.value(), HttpStatus.OK.name(), "the object are deleted."),
						HttpStatus.OK);
			}

			throw new ServerExciption("the dishes not exist");
		} catch (

		Exception e) {
			return new ResponseEntity<>(
					new ServerMessage(HttpStatus.NOT_FOUND.value(), HttpStatus.NOT_FOUND.name(), e.getMessage()),
					HttpStatus.NOT_FOUND);
		}

	}

	@DeleteMapping(value = { "/restaurants/{id}/dishes/{dishesid}",
			"/restaurants/name/{name}/dishes/{dishesid}" }, produces = { MediaType.APPLICATION_JSON_UTF8_VALUE,
					MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<?> deleteResto(@PathVariable(required = false, value = "") UUID id,
			@PathVariable(required = false, value = "") UUID dishesId,
			@PathVariable(required = false, value = "") String name) {

		Restaurant restaurant = facadeService.getRestaurantService().findById(id);
		Dishes dishes = null;

		for (int i = 0; i < restaurant.getDishes().size(); i++) {
			if (restaurant.getId() == dishesId)
				dishes = restaurant.getDishes().get(i);
		}

		if (dishes != null) {
			facadeService.getRestaurantService().deleteDishes(dishes);
			return new ResponseEntity<>(
					new ServerMessage(HttpStatus.OK.value(), HttpStatus.OK.name(), "the object are deleted."),
					HttpStatus.OK);
		} else {
			return new ResponseEntity<>(new ServerMessage(HttpStatus.NOT_FOUND.value(), HttpStatus.NOT_FOUND.name(),
					"the object dose not accepted."), HttpStatus.NOT_FOUND);
		}
	}

}
