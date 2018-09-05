package com.bigrestaurant.system.mall.services;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.geo.Circle;
import org.springframework.data.geo.Distance;
import org.springframework.data.geo.Metrics;
import org.springframework.data.geo.Point;
import org.springframework.data.mongodb.core.geo.GeoJsonPoint;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import com.bigrestaurant.system.mall.model.Mall;
import com.bigrestaurant.system.mall.model.MallOrders;
import com.bigrestaurant.system.operations.MongoOperations;
import com.bigrestaurant.system.operations.MongoOperations.MoreOperations;
import com.bigrestaurant.system.repositories.FacadeRepositry;
import com.bigrestaurant.system.restaurant.dishes.model.User;

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
	public static final Double KILOMETER = 111.0d;

	@SuppressWarnings("unchecked")
	@Override
	public <T, G> List<T> nearQuery(double max, G u) {

		try {
			Query query = new Query();
			
			System.out.println("in query near mall");

			// get user location in order.
			User user = (User) u;
			Point point = new Point(user.getLocation().getY(), user.getLocation().getX());
			GeoJsonPoint gjp = new GeoJsonPoint(point);
			
			Distance distance = new Distance(getMeter(max), Metrics.KILOMETERS);
			Circle circle = new Circle(gjp, distance);

			query.addCriteria(Criteria.where("locations").within(circle));
			
			System.out.println(facadeRepositry.getMongoTemplate().findOne(query, Mall.class).getName());
			
			return (List<T>) facadeRepositry.getMongoTemplate().find(query, Mall.class);
		}catch (Exception e) {
			return null;
		}
	}

	public Double getMeter(Double max) {
		return max / KILOMETER;
	}

}
