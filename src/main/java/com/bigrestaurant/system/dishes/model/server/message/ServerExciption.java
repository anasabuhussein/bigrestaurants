package com.bigrestaurant.system.dishes.model.server.message;

public class ServerExciption extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ServerExciption() {
		super();
	}

	public ServerExciption(String message, Throwable cause) {
		super(message, cause);
	}

	public ServerExciption(String message) {
		super(message);
	}

}
