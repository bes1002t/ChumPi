package com.raritan.chumpi.backend.rest.accessors;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

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
	private final long defaultLocation = 2803560;	// Zwickau
	
	@GET
	@Path("/get")
	public Weather getWeather(@QueryParam("location") long location) {
		if (location == 0)
			location = defaultLocation;
		return new WeatherProvider().parse(callWeather(Long.toString(location)));
	}

	private String callWeather(String location) {
		URL url = null;
		try {
			url = new URL(
					"http://api.openweathermap.org/data/2.5/weather?units=metric&id=" + location + "&appid=" + apikey);
			System.out.println(url);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		HttpURLConnection conn;
		BufferedReader br;
		StringBuilder sb = new StringBuilder();
		try {
			conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("GET");
			conn.setRequestProperty("Accept", "application/json");

			if (conn.getResponseCode() != 200) {
				throw new RuntimeException("Failed : HTTP error code : "
						+ conn.getResponseCode());
			}

			br = new BufferedReader(new InputStreamReader(
					(conn.getInputStream())));

			String output;
			while ((output = br.readLine()) != null) {
				sb.append(output);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return sb.toString();
	}
}
