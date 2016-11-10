package com.raritan.chumpi.backend.rest.serialization;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.raritan.chumpi.backend.data.Weather;

public abstract class GsonCreator {

	private Gson gson;

	protected void createGson() {
		GsonBuilder gsonBuilder = new GsonBuilder();
		gsonBuilder.registerTypeAdapter(Weather.class, new WeatherDeserializer());
		gson = gsonBuilder.create();
	}

	protected Gson getGson(){
		return gson;
	}
}
