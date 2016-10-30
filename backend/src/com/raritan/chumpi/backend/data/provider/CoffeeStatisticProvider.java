package com.raritan.chumpi.backend.data.provider;

import java.util.Date;
import java.util.stream.Collectors;

import com.raritan.chumpi.backend.data.CoffeeRecipe;
import com.raritan.chumpi.backend.data.User;

public class CoffeeStatisticProvider {
	
	public Statistic getAmount(CoffeeRecipe recipe, Date from, Date to) {
		return getAmountForUser(recipe, null, from, to);
	}

	public Statistic getAmountForUser(CoffeeRecipe recipe, User user, Date from, Date to) {
		int amount = OrderRepository.INSTANCE.getAllInstances().stream().filter(ord -> {
			boolean fromCond = ord.getDate().after(from);
			boolean toCond = ord.getDate().before(to);
			boolean recipeCond = ord.getRecipe().equals(recipe);
			boolean userCond = user == null? true : ord.getUser().equals(user);
			return fromCond && toCond && recipeCond && userCond;
		}).collect(Collectors.toList()).size();
		return new Statistic(user, recipe, amount);
	}
	
	public class Statistic {
		public final CoffeeRecipe recipe;
		public final User user;
		public final int amount;
		public Statistic(User user, CoffeeRecipe recipe, int amount) {
			this.user = user;
			this.recipe = recipe;
			this.amount = amount;
		}
	}
	
}
