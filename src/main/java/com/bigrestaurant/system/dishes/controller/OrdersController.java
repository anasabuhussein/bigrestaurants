package com.bigrestaurant.system.dishes.controller;

import java.rmi.ServerException;
import java.util.List;
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

import com.bigrestaurant.system.dishes.model.Orders;
import com.bigrestaurant.system.dishes.model.server.message.ServerMessage;
import com.bigrestaurant.system.services.FacadeService;

/**
 * @author Anas Abu Hussein
 * @since 28/8/2018
 **/
@RestController
public class OrdersController implements GeneralControllerPath {

	@Autowired
	FacadeService facadeService;

	/**
	 * **************************************************************************
	 * -----> Get Methods
	 * ***********************************************************************
	 **/

	/**
	 * Type : Get method.<br>
	 * Activity : To find all orders of dishes in system.
	 * 
	 * @return ResponseEntity List<Orders>
	 */
	@GetMapping(value = "/dishes/orders", produces = { MediaType.APPLICATION_JSON_UTF8_VALUE,
			MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<List<Orders>> findAllOrdersOfDishes() {
		return new ResponseEntity<>(facadeService.getOredreService().findAll(), HttpStatus.OK);
	}

	/**
	 * Type : Get method.<br>
	 * Activity : Find orders by id in system.
	 * 
	 * @param id UUID
	 * @return ResponseEntity Order
	 */
	@GetMapping(value = "/dishes/orders/{id}", produces = { MediaType.APPLICATION_JSON_UTF8_VALUE,
			MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<?> findOrdersOfDishesByID(@PathVariable("id") UUID id) {
		Orders orders = facadeService.getOredreService().findById(id);
		if (orders != null)
			return new ResponseEntity<>(orders, HttpStatus.OK);

		return new ResponseEntity<>(new ServerMessage(HttpStatus.NOT_FOUND.value(), HttpStatus.NOT_FOUND.name(),
				"the object not exist in database."), HttpStatus.NOT_FOUND);
	}

	/**
	 * Type : Get method.<br>
	 * Activity : Find orders by name of restaurant in system.
	 * 
	 * @param name String
	 * @return ResponseEntity User
	 */
	@GetMapping(value = "/dishes/orders/restaurant/{name}", produces = { MediaType.APPLICATION_JSON_UTF8_VALUE,
			MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<?> findOredersByRestaurantName(@PathVariable("name") String name) {

		List<Orders> orders = facadeService.getOredreService().findAllByEmbeddedObject(name);
		if (orders != null)
			if (!orders.isEmpty())
				return new ResponseEntity<>(orders, HttpStatus.OK);

		return new ResponseEntity<>(
				new ServerMessage(HttpStatus.NOT_FOUND.value(), HttpStatus.NOT_FOUND.name(),
						"the object of restaurant not exist in database; please login your restaurant."),
				HttpStatus.NOT_FOUND);

	}

	/**
	 * **************************************************************************
	 * -----> Post Methods
	 * ***********************************************************************
	 **/

	@PostMapping(value = { "/dishes/orders", "/dishes/orders/restaurant/{name}" }, produces = {
			MediaType.APPLICATION_JSON_UTF8_VALUE, MediaType.APPLICATION_XML_VALUE }, consumes = {
					MediaType.APPLICATION_JSON_UTF8_VALUE, MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<?> setNewOrders(@RequestBody Orders orders, @PathVariable("name") String name) {
		try {

			// for restaurant name.
			if (name != null)
				if (facadeService.getOredreService().saveWithName(orders, name) != null)
					return new ResponseEntity<>(
							new ServerMessage(HttpStatus.OK.value(), HttpStatus.OK.name(), "object saved"),
							HttpStatus.OK);

			// for orders embdded restaurant name.
			if (facadeService.getOredreService().save(orders) != null)
				return new ResponseEntity<>(
						new ServerMessage(HttpStatus.OK.value(), HttpStatus.OK.name(), "object saved"), HttpStatus.OK);

			throw new ServerException("must orders object not null");
		} catch (ServerException e) {
			return new ResponseEntity<>(
					new ServerMessage(HttpStatus.BAD_REQUEST.value(), HttpStatus.BAD_REQUEST.name(), e.getMessage()),
					HttpStatus.BAD_REQUEST);
		}
	}

	/**
	 * **************************************************************************
	 * -----> Put Methods
	 * ***********************************************************************
	 **/

	@PutMapping(value = { "/dishes/orders/{id}", "/dishes/orders/{id}/restaurant/{name}" }, produces = {
			MediaType.APPLICATION_JSON_UTF8_VALUE, MediaType.APPLICATION_XML_VALUE }, consumes = {
					MediaType.APPLICATION_JSON_UTF8_VALUE, MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<?> updateOrders(@RequestBody Orders orders, @PathVariable("name") String name,
			@PathVariable("id") UUID id) {
		try {

			if (orders != null)
				facadeService.getOredreService().update(orders, id);

			throw new ServerException("must orders object not null");
		} catch (ServerException e) {
			return new ResponseEntity<>(
					new ServerMessage(HttpStatus.BAD_REQUEST.value(), HttpStatus.BAD_REQUEST.name(), e.getMessage()),
					HttpStatus.BAD_REQUEST);
		}
	}

	/**
	 * **************************************************************************
	 * -----> Delete Methods
	 * ***********************************************************************
	 **/

	@DeleteMapping(value = { "/dishes/orders/{id}", "/dishes/orders/{id}/restaurant/{name}" }, produces = {
			MediaType.APPLICATION_JSON_UTF8_VALUE, MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<?> deleteOrdersByID(@PathVariable("name") String name, @PathVariable("id") UUID id) {
		try {

			Orders orders = facadeService.getOredreService().findById(id);

			if (orders != null)
				facadeService.getOredreService().delete(orders);

			throw new ServerException("must orders object not null");
		} catch (ServerException e) {
			return new ResponseEntity<>(
					new ServerMessage(HttpStatus.BAD_REQUEST.value(), HttpStatus.BAD_REQUEST.name(), e.getMessage()),
					HttpStatus.BAD_REQUEST);
		}
	}

	// the system delete all orders automaticly after got 7 dayes on any orders.
	// the system prvide it from order service class from class implements runnable.
	// and schedualed at fixed rate every 7 days.

}
