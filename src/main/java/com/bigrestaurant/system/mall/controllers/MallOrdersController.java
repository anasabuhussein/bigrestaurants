package com.bigrestaurant.system.mall.controllers;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bigrestaurant.system.restaurant.dishes.controller.GeneralControllerPath;
import com.bigrestaurant.system.services.FacadeService;

@RestController
public class MallOrdersController implements GeneralControllerPath {

	@Autowired
	private FacadeService facadeService;

	/**
	 * Get Method
	 **/

	@GetMapping(value = "/malls/orders", produces = { MediaType.APPLICATION_JSON_UTF8_VALUE,
			MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<?> findAll() {
		return new ResponseEntity<>(facadeService.getMallOrdersService().findAll(), HttpStatus.OK);
	}

	@GetMapping(value = "/malls/{id}/orders", produces = { MediaType.APPLICATION_JSON_UTF8_VALUE,
			MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<?> getOrdersByMallID(@PathVariable(name = "id") UUID id) {
		return new ResponseEntity<>(facadeService.getMallOrdersService().findAllByEmbeddedObject(id), HttpStatus.OK);
	}

	/**
	 * Post Method
	 **/

	// this if user set order without choose mall.
	// will get nearest mall 
	@PostMapping(value = "/malls/orders", produces = { MediaType.APPLICATION_JSON_UTF8_VALUE,
			MediaType.APPLICATION_XML_VALUE }, consumes = { MediaType.APPLICATION_JSON_UTF8_VALUE,
					MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<?> setNowOrders() {
		return new ResponseEntity<>(facadeService.getMallOrdersService().findAll(), HttpStatus.OK);
	}

	// this if user set order with choose mall.
	@PostMapping(value = "/malls/{id}/orders", produces = { MediaType.APPLICATION_JSON_UTF8_VALUE,
			MediaType.APPLICATION_XML_VALUE }, consumes = { MediaType.APPLICATION_JSON_UTF8_VALUE,
					MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<?> setNewOrders(@PathVariable(name = "id") UUID id) {
		return new ResponseEntity<>(facadeService.getMallOrdersService().findAllByEmbeddedObject(id), HttpStatus.OK);
	}

	/**
	 * Put Method
	 **/

	/**
	 * Delete Method
	 **/
}
