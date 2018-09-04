package com.bigrestaurant.system.mall.model;

import java.util.UUID;

import com.fasterxml.uuid.Generators;

public class Item {

	private UUID id;

	private String name;

	private double amount;

	private double price;

	private boolean exist;

	public Item() {
		super();
		if (id == null) {
			this.id = Generators.timeBasedGenerator().generate();
		}
	}

	public Item(String name, double amount, double price, boolean exist) {
		super();
		this.id = Generators.timeBasedGenerator().generate();
		this.name = name;
		this.amount = amount;
		this.price = price;
		this.exist = exist;
	}

	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public boolean isExist() {
		return exist;
	}

	public void setExist(boolean exist) {
		this.exist = exist;
	}

}
