package com.bigrestaurant.system.dishes.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class FacadeService {

	@Autowired
	private DishesService dishesService;

	@Autowired
	private ObjectMapper objectMapper;

	@Autowired
	private OredreService oredreService;

	@Autowired
	private UserService userService;

	public FacadeService() {
		super();
	}

	public DishesService getDishesService() {
		return dishesService;
	}

	public ObjectMapper getObjectMapper() {
		return objectMapper;
	}

	public OredreService getOredreService() {
		return oredreService;
	}

	public UserService getUserService() {
		return userService;
	}

}
