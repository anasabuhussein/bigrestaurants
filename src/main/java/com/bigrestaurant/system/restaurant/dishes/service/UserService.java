package com.bigrestaurant.system.restaurant.dishes.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Service;

import com.bigrestaurant.system.operations.MongoOperations;
import com.bigrestaurant.system.repositories.FacadeRepositry;
import com.bigrestaurant.system.restaurant.dishes.model.User;

@Service
@Order(2)
public class UserService implements MongoOperations<User> {

	@Autowired
	private FacadeRepositry facadeRepositry;

	@Override
	public List<User> findAll() {
		return facadeRepositry.getUserRepo().findAll();
	}

	@Override
	public <K> User findById(K object) {
		Optional<User> user = facadeRepositry.getUserRepo().findById((String) object);
		if (user.isPresent())
			return user.get();
		return null;
	}

	@Override
	public User findByObject(User object) {
		return null;
	}

	@Override
	public User save(User object) {
		return facadeRepositry.getUserRepo().save(object);
	}

	@SuppressWarnings("hiding")
	@Override
	public <String> User update(User object, String id) {
		User user = this.findById(id);

		if (object.getId() != null)
			user.setId(object.getId());

		if (object.getName() != null)
			user.setName(object.getName());

		if (object.getEmail() != null)
			user.setEmail(object.getEmail());

		if (object.getPhone() != null)
			user.setPhone(object.getPhone());

		if (object.getLocation() != null)
			user.setLocation(object.getLocation());

		if (object.getPic() != null)
			user.setPic(object.getPic());

		if (object.getTableNum() != 0)
			user.setTableNum(object.getTableNum());

		return facadeRepositry.getUserRepo().save(user);
	}

	@Override
	public void delete(User object) {
		facadeRepositry.getUserRepo().delete(object);
	}

	@Override
	public <G> User findByEmbeddedObject(G name) {
		return null;
	}

	@Override
	public <G> List<User> findAllByEmbeddedObject(G name) {
		return facadeRepositry.getUserRepo().findByName((String) name);
	}

	@Override
	public boolean iterator(User t) {
		return false;
	}

	public FacadeRepositry getFacadeRepositry() {
		return facadeRepositry;
	}

}
