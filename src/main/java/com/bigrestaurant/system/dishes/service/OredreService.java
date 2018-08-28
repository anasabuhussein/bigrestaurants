package com.bigrestaurant.system.dishes.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Service;

import com.bigrestaurant.system.dishes.model.Orders;
import com.bigrestaurant.system.dishes.repo.DishesRepo;
import com.bigrestaurant.system.dishes.repo.OrdersRepo;
import com.bigrestaurant.system.dishes.repo.UserRepo;

@Service
@Order(3)
@SuppressWarnings("unused")
public class OredreService implements CommandLineRunner {

//	@Autowired
//	private DishesRepo dishesRepo;
//
//	@Autowired
//	private OrdersRepo userDishesTransactionRepo;
//
//	@Autowired
//	private UserRepo userRepo;

	@Override
	public void run(String... args) throws Exception {

//		for (int i = 0; i < 20000; i++) {
//			Optional<User> user = userRepo.findById((long) 1);
//			Dishes di = dishesRepo.findAll().get(0);
//
//			Set<Dishes> dis = new HashSet<>();
//
//			di.setAdditonalComponent(null);
//			di.setRating(new RatingOperations());
//
//			Orders udt = new Orders(user.get(), dis);
//			udt.setUserRating(5);
//
//			long totalRating = di.getRating().getTotalRating() + udt.getUserRating();
//			long totalUser = di.getRating().getTotalUserRate() + 1;
//
//			di.getRating().setTotalRating(totalRating);
//			di.getRating().setTotalUserRate(totalUser);
//
//			dishesRepo.save(di);
//			dis.add(di);
//
//			userDishesTransactionRepo.save(udt);
//
//			List<String> l = new ArrayList<>();
//			l.add("anas");
//			l.add("bassam");
//
//			Orders udt2 = new Orders(user.get(), dis);
//
//			udt2.setUserRating(10);
//
//			totalRating = di.getRating().getTotalRating() + udt2.getUserRating();
//			totalUser = di.getRating().getTotalUserRate() + 1;
//
//			di.getRating().setTotalRating(totalRating);
//			di.getRating().setTotalUserRate(totalUser);
//
//			dishesRepo.save(di);
//
//			di.setAdditonalComponent(l);
//			userDishesTransactionRepo.save(udt2);
//		}
	}

	public List<Orders> getUserDishesTransactionRepo() {
//		return userDishesTransactionRepo.findAll();
		return null;
	}

}
