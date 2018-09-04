package com.bigrestaurant.system.mall.controllers;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.bigrestaurant.system.dishes.controller.GeneralControllerPath;
import com.bigrestaurant.system.dishes.model.server.message.ServerMessage;
import com.bigrestaurant.system.mall.model.Mall;
import com.bigrestaurant.system.services.FacadeService;

public class MallController implements GeneralControllerPath {

	@Autowired
	private FacadeService facadeService;

	/**
	 * Get Method
	 **/
	@GetMapping(value = "/malls", produces = { MediaType.APPLICATION_JSON_UTF8_VALUE, MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<?> findAll() {
		return new ResponseEntity<>(facadeService.getMallService().findAll(), HttpStatus.OK);
	}

	@GetMapping(value = "/malls/{id}", produces = { MediaType.APPLICATION_JSON_UTF8_VALUE,
			MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<?> findById(@PathVariable UUID id) {
		return new ResponseEntity<>(facadeService.getMallService().findById(id), HttpStatus.OK);
	}

	/**
	 * Post Method
	 **/
	@PostMapping(value = "/malls", produces = { MediaType.APPLICATION_JSON_UTF8_VALUE,
			MediaType.APPLICATION_XML_VALUE }, consumes = { MediaType.APPLICATION_JSON_UTF8_VALUE,
					MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<?> addNewMall(@RequestBody Mall mall) {
		if (mall != null)
			if (facadeService.getMallService().save(mall) != null)
				return new ResponseEntity<>(
						new ServerMessage(HttpStatus.CREATED.value(), HttpStatus.CREATED.name(), "object are created."),
						HttpStatus.CREATED);

		return new ResponseEntity<>(new ServerMessage(HttpStatus.BAD_REQUEST.value(), HttpStatus.BAD_REQUEST.name(),
				"not accept the object"), HttpStatus.BAD_REQUEST);
	}

	/**
	 * Put Method
	 **/
	@PostMapping(value = "/malls/{id}", produces = { MediaType.APPLICATION_JSON_UTF8_VALUE,
			MediaType.APPLICATION_XML_VALUE }, consumes = { MediaType.APPLICATION_JSON_UTF8_VALUE,
					MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<?> updateMall(@RequestBody Mall mall, @PathVariable UUID id) {
		if (mall != null)
			if (facadeService.getMallService().update(mall, id) != null)
				return new ResponseEntity<>(new ServerMessage(HttpStatus.ACCEPTED.value(), HttpStatus.ACCEPTED.name(),
						"object are updated."), HttpStatus.ACCEPTED);

		return new ResponseEntity<>(new ServerMessage(HttpStatus.BAD_REQUEST.value(), HttpStatus.BAD_REQUEST.name(),
				"not accept the object"), HttpStatus.BAD_REQUEST);
	}

	/**
	 * Delete Method
	 **/
	@PostMapping(value = "/malls/{id}", produces = { MediaType.APPLICATION_JSON_UTF8_VALUE,
			MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<?> deleteMAll(@PathVariable UUID id) {

		Mall mall = facadeService.getMallService().findById(id);
		if (mall != null) {
			facadeService.getMallService().delete(mall);
			return new ResponseEntity<>(
					new ServerMessage(HttpStatus.ACCEPTED.value(), HttpStatus.ACCEPTED.name(), "object are updated."),
					HttpStatus.ACCEPTED);
		}

		return new ResponseEntity<>(new ServerMessage(HttpStatus.BAD_REQUEST.value(), HttpStatus.BAD_REQUEST.name(),
				"not exist the object"), HttpStatus.BAD_REQUEST);
	}
}
