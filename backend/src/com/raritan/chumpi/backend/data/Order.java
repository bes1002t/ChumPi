package com.raritan.chumpi.backend.data;

import java.util.Date;

public class Order {
	
	private static int latestId = 0;
	private final int orderId;
	private final User user;
	private final CoffeeRecipe recipe;
	private final Date date;
	
	public Order(User user, CoffeeRecipe recipe, Date date) {
		this(latestId++, user, recipe, date);
	}
	
	public Order(int id, User user, CoffeeRecipe recipe, Date date) {
		this.orderId = id;
		this.user = user;
		this.recipe = recipe;
		this.date = date;
	}
	
	public Order(User user, CoffeeRecipe recipe) {
		this(user, recipe, new Date());
	}

	public User getUser() {
		return user;
	}

	public CoffeeRecipe getRecipe() {
		return recipe;
	}

	public Date getDate() {
		return date;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (! (obj instanceof Order))
			return false;
		
		Order other = (Order) obj;
		return (other.user == null ? user == null : other.user.equals(user)) &&
				other.recipe.equals(recipe) &&
				other.date.equals(date);
	}
	
	@Override
	public String toString() {
		return Long.toString(date.getTime() / 1000);
	}
	
	@Override
	public int hashCode() {
		int result = user != null ? user.hashCode() : 0;
		result = result * 31 + orderId;
		result = result * 31 + recipe.hashCode();
		result = result * 31 + date.hashCode();
		return result;
	}

}
