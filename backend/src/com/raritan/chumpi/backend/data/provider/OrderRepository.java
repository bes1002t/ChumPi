package com.raritan.chumpi.backend.data.provider;

import com.raritan.chumpi.backend.data.CoffeeRecipe;
import com.raritan.chumpi.backend.data.Order;
import com.raritan.chumpi.backend.data.User;

public class OrderRepository extends AbstractRepository<Order> {
	
	public final static OrderRepository INSTANCE = new OrderRepository();
	
	private OrderRepository() {}

	public Order createOrder(User user, CoffeeRecipe recipe) {
		Order o = new Order(user, recipe);
		cache.add(o);
		persist(o);
		return o;
	}

	@Override
	protected Class<Order> getRepoType() {
		return Order.class;
	}
	
}
