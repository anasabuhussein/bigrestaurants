package com.bigrestaurant.system.mall.services;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;

import com.bigrestaurant.system.mall.model.Mall;
import com.bigrestaurant.system.operations.MongoOperations;
import com.bigrestaurant.system.repositories.FacadeRepositry;

public class MallService implements MongoOperations<Mall> {

	@Autowired
	private FacadeRepositry facadeRepositry;

	@Override
	public List<Mall> findAll() {
		return facadeRepositry.getMallRepo().findAll();
	}

	@Override
	public <K> Mall findById(K object) {
		Optional<Mall> mall = facadeRepositry.getMallRepo().findById((UUID) object);
		if (mall.isPresent())
			return mall.get();
		return null;
	}

	@Override
	public Mall findByObject(Mall object) {
		return null;
	}

	@Override
	public Mall save(Mall object) {
		if (object != null)
			return facadeRepositry.getMallRepo().save(object);
		return null;
	}

	@SuppressWarnings("hiding")
	@Override
	public <UUID> Mall update(Mall object, UUID id) {
		Mall mall = findById(id);

		if (mall == null)
			return null;

		if (object.getName() != null)
			mall.setName(object.getName());

		if (object.getCity() != null)
			mall.setCity(object.getCity());

		if (object.getCountry() != null)
			mall.setCountry(object.getCountry());

		if (object.getLocations() != null)
			mall.setLocations(object.getLocations());

		return this.save(mall);
	}

	@Override
	public void delete(Mall object) {
		facadeRepositry.getMallRepo().delete(object);
	}

	@Override
	public <G> Mall findByEmbeddedObject(G m) {
		return null;
	}

	@Override
	public <G> List<?> findAllByEmbeddedObject(G m) {
		return null;
	}

	@Override
	public boolean iterator(Mall t) {
		return false;
	}

}
