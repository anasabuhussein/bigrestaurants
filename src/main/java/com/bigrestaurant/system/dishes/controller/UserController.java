package com.bigrestaurant.system.dishes.controller;

import java.util.List;

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

import com.bigrestaurant.system.dishes.model.User;
import com.bigrestaurant.system.dishes.model.server.message.ServerExciption;
import com.bigrestaurant.system.dishes.model.server.message.ServerMessage;
import com.bigrestaurant.system.dishes.service.FacadeService;

/**
 * @author Anas Abu Hussein
 * @since 16/8/2018
 **/
@RestController
public class UserController implements GeneralControllerPath {

	@Autowired
	FacadeService facadeService;

	/**
	 * **************************************************************************
	 * -----> Get Methods
	 * ***********************************************************************
	 **/

	/**
	 * Type : Get method.<br>
	 * Activity : To find all users in system.
	 * 
	 * @return ResponseEntity List<User>
	 */
	@GetMapping(value = "/users", produces = { MediaType.APPLICATION_JSON_UTF8_VALUE, MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<List<User>> getAllUsers() {
		return new ResponseEntity<>(facadeService.getUserService().findAll(), HttpStatus.OK);
	}

	/**
	 * Type : Get method.<br>
	 * Activity : Find user by id in system.
	 * 
	 * @param id String
	 * @return ResponseEntity User
	 */
	@GetMapping(value = "/users/{id}", produces = { MediaType.APPLICATION_JSON_UTF8_VALUE,
			MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<?> getUserByID(@PathVariable("id") String id) {

		User user = facadeService.getUserService().findById(id);
		if (user != null)
			return new ResponseEntity<>(user, HttpStatus.OK);

		return new ResponseEntity<>(new ServerMessage(HttpStatus.NOT_FOUND.value(), HttpStatus.NOT_FOUND.name(),
				"the object not exist in database."), HttpStatus.NOT_FOUND);
	}

	/**
	 * Type : Get method.<br>
	 * Activity : Find user by name in system.
	 * 
	 * @param name String
	 * @return ResponseEntity User
	 */
	@GetMapping(value = "/users/name/{name}", produces = { MediaType.APPLICATION_JSON_UTF8_VALUE,
			MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<?> getUserByName(@PathVariable("name") String name) {

		List<User> user = facadeService.getUserService().findAllByEmbeddedObject(name);
		if (user != null)
			return new ResponseEntity<>(user, HttpStatus.OK);

		return new ResponseEntity<>(new ServerMessage(HttpStatus.NOT_FOUND.value(), HttpStatus.NOT_FOUND.name(),
				"the object not exist in database."), HttpStatus.NOT_FOUND);

	}

	/**
	 * **************************************************************************
	 * -----> Post Methods
	 * ***********************************************************************
	 **/

	/**
	 * Type : Post method.<br>
	 * Activity : save object of user.
	 * 
	 * @param user User
	 * @return ResponseEntity<ServerMessage>
	 **/
	@PostMapping(value = "/users", produces = { MediaType.APPLICATION_JSON_UTF8_VALUE,
			MediaType.APPLICATION_XML_VALUE }, consumes = { MediaType.APPLICATION_JSON_UTF8_VALUE,
					MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<ServerMessage> setNewUser(@RequestBody User user) {
		try {

			if (facadeService.getDishesService().findById(user.getId()) == null)
				if (facadeService.getUserService().save(user) != null)
					return new ResponseEntity<>(
							new ServerMessage(HttpStatus.OK.value(), HttpStatus.OK.name(), "the object is saved"),
							HttpStatus.OK);

			if (facadeService.getDishesService().findById(user.getId()) != null)
				throw new ServerExciption("user is exist before.");

			throw new ServerExciption();

		} catch (ServerExciption e) {
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

	@PutMapping(value = "/users/{id}", produces = { MediaType.APPLICATION_JSON_UTF8_VALUE,
			MediaType.APPLICATION_XML_VALUE }, consumes = { MediaType.APPLICATION_JSON_UTF8_VALUE,
					MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<ServerMessage> updateUser(@RequestBody User user, @PathVariable("id") String id)
			throws ServerExciption {

		try {

			if (facadeService.getUserService().update(user, id) != null)
				return new ResponseEntity<>(
						new ServerMessage(HttpStatus.OK.value(), HttpStatus.OK.name(), "the object is updated"),
						HttpStatus.OK);

			throw new ServerExciption();
		} catch (ServerExciption e) {
			return new ResponseEntity<>(new ServerMessage(HttpStatus.NOT_FOUND.value(), HttpStatus.NOT_FOUND.name(),
					"the object is not found in database."), HttpStatus.NOT_FOUND);
		}
	}

	/**
	 * **************************************************************************
	 * -----> Delete Methods
	 * ***********************************************************************
	 **/

	@DeleteMapping(value = "/users/{id}", produces = { MediaType.APPLICATION_JSON_UTF8_VALUE,
			MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<ServerMessage> deleteUser(@PathVariable("id") String id) throws ServerExciption {

		try {

			User user = facadeService.getUserService().findById(id);
			if (user != null)
				facadeService.getUserService().delete(user);

			user = facadeService.getUserService().findById(id);
			if (user == null)
				throw new ServerExciption("the object was deleted.");

			return null;
		} catch (ServerExciption e) {
			return new ResponseEntity<>(
					new ServerMessage(HttpStatus.NOT_FOUND.value(), HttpStatus.NOT_FOUND.name(), e.getMessage()),
					HttpStatus.NOT_FOUND);
		}
	}

}
