package com.bigrestaurant.system.mall.services;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import com.bigrestaurant.system.mall.model.MallOrders;
import com.bigrestaurant.system.operations.MongoOperations;
import com.bigrestaurant.system.operations.MongoOperations.MoreOperations;
import com.bigrestaurant.system.repositories.FacadeRepositry;

@Service
public class MallOrdersService implements MongoOperations<MallOrders>, MoreOperations {

	@Autowired
	private FacadeRepositry facadeRepositry;

	@Override
	public List<MallOrders> findAll() {
		return facadeRepositry.getMallOrdersRepo().findAll();
	}

	@Override
	public <K> MallOrders findById(K object) {
		Optional<MallOrders> mallOrders = facadeRepositry.getMallOrdersRepo().findById((UUID) object);
		if (mallOrders.isPresent())
			return mallOrders.get();
		return null;
	}

	@Override
	public MallOrders findByObject(MallOrders object) {
		return null;
	}

	@Override
	public MallOrders save(MallOrders object) {
		MallOrders mallOrders = facadeRepositry.getMallOrdersRepo().save(object);
		if (mallOrders != null)
			return mallOrders;
		return null;
	}

	@Override
	public <G> MallOrders update(MallOrders object, G g) {
		return null;
	}

	@Override
	public void delete(MallOrders object) {
		facadeRepositry.getMallOrdersRepo().delete(object);
	}

	@Override
	public <G> MallOrders findByEmbeddedObject(G m) {
		return null;
	}

	@Override
	public <G> List<?> findAllByEmbeddedObject(G m) {
		Query query = new Query();
		query.addCriteria(Criteria.where("mallID").all(m));
		return facadeRepositry.getMongoTemplate().find(query, MallOrders.class);
	}

	@Override
	public boolean iterator(MallOrders t) {
		return false;
	}

	// get near mall to user ...
	@Override
	public <T> T nearQuery() {
		return null;
	}

}
