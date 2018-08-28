package com.bigrestaurant.system.dishes.model;

import com.bigrestaurant.system.dishes.view.View;
import com.fasterxml.jackson.annotation.JsonView;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

@JacksonXmlRootElement
public class Comments {

	@JsonView(View.CommentsOfDishes.class)
	private String comment;

	@JsonView(View.CommentsOfDishes.class)
	private User user;

	@JsonView(View.CommentsOfDishes.class)
	private int index;

	public Comments() {
		super();
	}

	public Comments(String comments, User user) {
		super();
		this.comment = comments;
		this.user = user;
	}

	public Comments(String comment, User user, int index) {
		super();
		this.comment = comment;
		this.user = user;
		this.index = index;
	}

	public String getComment() {
		return comment;
	}

	public User getUser() {
		return user;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}

}
