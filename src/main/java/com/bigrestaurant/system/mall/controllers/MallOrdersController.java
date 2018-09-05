package com.bigrestaurant.system.mall.controllers;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.bigrestaurant.system.mall.model.Mall;
import com.bigrestaurant.system.mall.model.MallOrders;
import com.bigrestaurant.system.mall.model.NearestMallOrder;
import com.bigrestaurant.system.restaurant.dishes.controller.GeneralControllerPath;
import com.bigrestaurant.system.restaurant.dishes.model.server.message.ServerMessage;
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

	@SuppressWarnings("unused")
	// this if user set order without choose mall.
	// will get nearest mall
	// if user chooce the mall will avoid the range param.!
	@PostMapping(value = { "/malls/orders", "/malls/{id}/orders" }, produces = { MediaType.APPLICATION_JSON_UTF8_VALUE,
			MediaType.APPLICATION_XML_VALUE }, consumes = { MediaType.APPLICATION_JSON_UTF8_VALUE,
					MediaType.APPLICATION_XML_VALUE })
	public @ResponseBody ResponseEntity<?> setNowOrders(@RequestBody(required = true) MallOrders mallOrders,
			@RequestParam(required = false, value = "range") String range, @PathVariable(value = "id") UUID id) {

		NearestMallOrder nearestMallOrder = new NearestMallOrder();

		if (range != null && id == null)
			if (range != "") {
				nearestMallOrder.setMalls(facadeService.getMallOrdersService().nearQuery(Double.parseDouble(range),
						mallOrders.getUser()));

				nearestMallOrder.setMallOrders(mallOrders);
				return new ResponseEntity<>(nearestMallOrder, HttpStatus.OK);
			}

		Mall mall = null;

		if (id != null && range == null)
			mall = facadeService.getMallService().findById(id);

		if (mall != null) {
			mallOrders.setMallID(mall.getId());
			mallOrders.setMallName(mall.getName());

			facadeService.getMallOrdersService().save(mallOrders);

			return new ResponseEntity<>(
					new ServerMessage(HttpStatus.CREATED.value(), HttpStatus.CREATED.name(), "the object are created."),
					HttpStatus.CREATED);
		}

		return new ResponseEntity<>(new ServerMessage(HttpStatus.NOT_FOUND.value(), HttpStatus.NOT_FOUND.name(),
				"the object are not found in database."), HttpStatus.NOT_FOUND);

	}

	/**
	 * Put Method
	 **/

	/**
	 * Delete Method
	 **/
}
