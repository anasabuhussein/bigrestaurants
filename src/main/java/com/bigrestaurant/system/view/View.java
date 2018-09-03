package com.bigrestaurant.system.view;

public interface View {

	public interface DishesView {
	}

	public interface DishesViewWithLinks {
	}

	public interface UserView {
	}

	public interface CommentsOfDishes {
	}

	public class MainComponent implements DishesView, DishesViewWithLinks, CommentsOfDishes {
	}

	public class ServerMessageView implements DishesView, DishesViewWithLinks, UserView, CommentsOfDishes {

	}

	public class RestaurantView extends MainComponent {

	}

}
