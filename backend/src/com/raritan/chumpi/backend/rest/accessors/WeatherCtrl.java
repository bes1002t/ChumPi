package com.raritan.chumpi.backend.rest.accessors;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;

import com.raritan.chumpi.backend.data.Weather;
import com.raritan.chumpi.backend.data.provider.WeatherProvider;

@Path("/weather")
public class WeatherCtrl {

	// chumpi_ha(@byom.de)
	// chumpi2016
	private final String apikey = "a09a135075c61ac050a7115d92d5f487";
	private final long defaultLocation = 2803560; // Zwickau

	@GET
	@Path("/get")
	public Weather getWeather(@QueryParam("location") long location) {
		try {
			if (location == 0)
				location = defaultLocation;
			return new WeatherProvider()
					.parse(new RestApiCaller()
					.call("http://api.openweathermap.org/data/2.5/weather?units=metric&id=" + location + "&appid=" + apikey));
		} catch(Exception e) {
			e.printStackTrace();
			return null;
		}

	}

}
