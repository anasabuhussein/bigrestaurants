package com.bigrestaurant.system.dishes.model;

import com.fasterxml.jackson.annotation.JsonView;

@JsonView(com.bigrestaurant.system.dishes.view.View.DishesView.class)
public class HATEOAS {

	private String href;
	private String rel;

	public HATEOAS(String href, String rel) {
		super();
		this.href = href;
		this.rel = rel;
	}

	public String getHref() {
		return href;
	}

	public void setHref(String href) {
		this.href = href;
	}

	public String getRel() {
		return rel;
	}

	public void setRel(String rel) {
		this.rel = rel;
	}

}
