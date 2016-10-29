package com.raritan.chumpi.backend.data;

import java.time.LocalDate;

public class Order {
	
	private static int latestId = 0;
	private final int orderId;
	private final User user;
	private final CoffeeRecipe recipe;
	private final LocalDate date;
	
	public Order(User user, CoffeeRecipe recipe, LocalDate date) {
		this(latestId++, user, recipe, date);
	}
	
	public Order(int id, User user, CoffeeRecipe recipe, LocalDate date) {
		this.orderId = id;
		this.user = user;
		this.recipe = recipe;
		this.date = date;
	}
	
	public Order(User user, CoffeeRecipe recipe) {
		this(user, recipe, LocalDate.now());
	}

	public User getUser() {
		return user;
	}

	public CoffeeRecipe getRecipe() {
		return recipe;
	}

	public LocalDate getDate() {
		return date;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (! (obj instanceof Order))
			return false;
		
		Order other = (Order) obj;
		return other.user.equals(user) &&
				other.recipe.equals(recipe) &&
				other.date.equals(date);
	}
	
	@Override
	public String toString() {
		return "" + orderId + "_" + user.getId();
	}
	
	@Override
	public int hashCode() {
		int result = user.hashCode();
		result = result * 31 + orderId;
		result = result * 31 + recipe.hashCode();
		result = result * 31 + user.hashCode();
		result = result * 31 + date.hashCode();
		return result;
	}

}
