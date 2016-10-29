package com.raritan.chumpi.backend.rest.accessors;

import java.net.URI;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;

import com.raritan.chumpi.backend.data.Weather;

@Path("/weather")
public class WeatherCtrl {

	private URI baseUri;

	@GET
	@Path("/get")
	public Weather getWeather(@QueryParam("location") int location) {
		return null;
	}
}
