package com.bigrestaurant.system.dishes.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
	public ResponseEntity<List<Orders>> getAllUsers() {
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
	public ResponseEntity<?> getUserByID(@PathVariable("id") UUID id) {

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
	public ResponseEntity<?> getUserByName(@PathVariable("name") String name) {

		List<Orders> orders = facadeService.getOredreService().findAllByEmbeddedObject(name);
		if (orders != null)
			if (!orders.isEmpty())
				return new ResponseEntity<>(orders, HttpStatus.OK);

		return new ResponseEntity<>(new ServerMessage(HttpStatus.NOT_FOUND.value(), HttpStatus.NOT_FOUND.name(),
				"the object of restaurant not exist in database; please login your restaurant."), HttpStatus.NOT_FOUND);

	}

	/**
	 * **************************************************************************
	 * -----> Post Methods
	 * ***********************************************************************
	 **/

	/**
	 * **************************************************************************
	 * -----> Put Methods
	 * ***********************************************************************
	 **/

	/**
	 * **************************************************************************
	 * -----> Delete Methods
	 * ***********************************************************************
	 **/

}
