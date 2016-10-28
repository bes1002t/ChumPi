package com.raritan.chumpi.backend.data;

public class UserSettings {

	private CoffeeRecipe preferredCoffee = null;

	public static UserSettings initialSetting() {
		UserSettings setting = new UserSettings();
		setting.preferredCoffee = CoffeeRecipe.DEFAULT;
		return setting;
	}
	
	@Override
	public String toString() {
		return preferredCoffee.toString();
	};
	
}
