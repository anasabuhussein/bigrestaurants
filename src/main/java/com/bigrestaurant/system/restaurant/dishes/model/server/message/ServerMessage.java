package com.bigrestaurant.system.restaurant.dishes.model.server.message;

public class ServerMessage {
	private int httpCode;
	private String state;
	private String message;

	public ServerMessage() {
		super();
	}

	public ServerMessage(int httpCode, String state, String message) {
		super();
		this.httpCode = httpCode;
		this.state = state;
		this.message = message;
	}

	public int getHttpCode() {
		return httpCode;
	}

	public void setHttpCode(int httpCode) {
		this.httpCode = httpCode;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
