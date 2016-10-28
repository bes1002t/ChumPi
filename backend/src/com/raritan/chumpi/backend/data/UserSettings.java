package com.raritan.chumpi.backend.data;

import java.util.ArrayList;
import java.util.List;

import com.raritan.chumpi.backend.IApp;

public class UserSettings {

	private CoffeeRecipe preferredCoffee = null;
	private final List<IApp> appListing = new ArrayList<>();

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
