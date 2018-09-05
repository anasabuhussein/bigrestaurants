package com.bigrestaurant.system.mall.model;

import java.util.ArrayList;
import java.util.List;

public class NearestMallOrder {

	private List<Mall> malls = new ArrayList<>();
	private MallOrders mallOrders;

	public NearestMallOrder() {
		super();
	}

	public NearestMallOrder(List<Mall> malls, MallOrders mallOrders) {
		super();
		this.malls = malls;
		this.mallOrders = mallOrders;
	}

	public List<Mall> getMalls() {
		return malls;
	}

	public void setMalls(List<Mall> malls) {
		this.malls = malls;
	}

	public MallOrders getMallOrders() {
		return mallOrders;
	}

	public void setMallOrders(MallOrders mallOrders) {
		this.mallOrders = mallOrders;
	}

}
