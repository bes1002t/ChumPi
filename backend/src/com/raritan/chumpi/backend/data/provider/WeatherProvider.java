package com.raritan.chumpi.backend.data.provider;

import com.raritan.chumpi.backend.data.Weather;
import com.raritan.chumpi.backend.rest.server.GsonCreator;

public class WeatherProvider extends GsonCreator {
	public WeatherProvider() {
		createGson();
	}
	
	public Weather parse(String json) {
		// FIXME json not parsed successful
		return getGson().fromJson(json, Weather.class);
	}
}