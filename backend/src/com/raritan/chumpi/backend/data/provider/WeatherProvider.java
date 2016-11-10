package com.raritan.chumpi.backend.data.provider;

import java.io.IOException;

import com.raritan.chumpi.backend.data.Weather;
import com.raritan.chumpi.backend.rest.ThirdPartyRestProvider;
import com.raritan.chumpi.backend.rest.serialization.GsonCreator;

public class WeatherProvider extends GsonCreator {

	private ThirdPartyRestProvider requestSender;

	// chumpi_ha(@byom.de)
	// chumpi2016
	private final String url = "http://api.openweathermap.org";
	private final String apikey = "a09a135075c61ac050a7115d92d5f487";

	public WeatherProvider() {
		createGson();

		requestSender = new ThirdPartyRestProvider();
	}

	public Weather getWeatherInfo(long location) throws IOException, RuntimeException {
		String apiUri = String.format("%s/data/2.5/forecast?units=metric&id=%d&appid=%s", url, location, apikey);
		String apiResult = requestSender.send(apiUri);
		Weather weather = getGson().fromJson(apiResult, Weather.class);

		return weather;
	}
}