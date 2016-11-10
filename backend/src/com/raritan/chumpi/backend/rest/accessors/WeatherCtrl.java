package com.raritan.chumpi.backend.rest.accessors;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;

import com.raritan.chumpi.backend.data.Weather;
import com.raritan.chumpi.backend.data.provider.WeatherProvider;

@Path("/weather")
public class WeatherCtrl {

	private final long defaultLocation = 2803560; // Zwickau
	private WeatherProvider provider;

	public WeatherCtrl() {
		provider = new WeatherProvider();
	}

	@GET
	@Path("/get")
	public Weather getWeather(@QueryParam("location") long location) {
		try {
			long tmpLocation = defaultLocation;

			if (location != 0) tmpLocation = location;

			return provider.getWeatherInfo(tmpLocation);
		} catch(Exception e) {
			e.printStackTrace();
			return null;
		}
	}
}
