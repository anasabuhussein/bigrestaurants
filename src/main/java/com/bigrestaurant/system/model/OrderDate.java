package com.bigrestaurant.system.model;

import java.time.LocalDate;
import java.time.LocalTime;

public class OrderDate{

	private String history;

	private String oclock;

	public OrderDate(String history, String oclock) {
		super();
		this.history = history;
		this.oclock = oclock;
	}

	/**
	 * @return the history
	 */
	public String getHistory() {
		return history;
	}

	/**
	 * @return the oclock
	 */
	public String getOclock() {
		return oclock;
	}

	/**
	 * @param history the history to set
	 */
	public void setHistory(String history) {
		this.history = history;
	}

	/**
	 * @param oclock the oclock to set
	 */
	public void setOclock(String oclock) {
		this.oclock = oclock;
	}

	public static OrderDate orderDate() {
		LocalDate hNow = LocalDate.now();
		LocalTime tNow = LocalTime.now();

		return new OrderDate(hNow.toString(), tNow.toString());
	}
}
