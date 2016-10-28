package com.raritan.chumpi.backend.rest.server;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public abstract class GsonCreator {

	private Gson gson;

	protected void createGson() {
		GsonBuilder gsonBuilder = new GsonBuilder();
		gson = gsonBuilder.create();
	}

	protected Gson getGson(){
		return gson;
	}
}
