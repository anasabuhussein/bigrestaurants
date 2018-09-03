package com.bigrestaurant.system.dishes.model;

import com.bigrestaurant.system.view.View;
import com.fasterxml.jackson.annotation.JsonView;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

@JsonView(View.DishesView.class)
@JacksonXmlRootElement
public class RatingOperations {

	private long totalRating;
	private long totalUserRate;

	public RatingOperations() {
		super();
	}

	public RatingOperations(long totalRating, long totalUserRate, long rating) {
		super();
		this.totalRating = totalRating;
		this.totalUserRate = totalUserRate;
	}

	public long getTotalRating() {
		return totalRating;
	}

	public void setTotalRating(long totalRating) {
		this.totalRating = totalRating;
	}

	public long getTotalUserRate() {
		return totalUserRate;
	}

	public void setTotalUserRate(long totalUserRate) {
		this.totalUserRate = totalUserRate;
	}

	public long getRating() {
		return totalRating / totalUserRate;
	}

}
