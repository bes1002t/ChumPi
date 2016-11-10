package com.raritan.chumpi.backend.data;

import java.util.ArrayList;
import java.util.List;

public class Weather {

	private String city;
	private List<WeatherData> forecast = new ArrayList<WeatherData>();

	public void addWeatherData(WeatherData data) {
		forecast.add(data);
	}

	public void setCity(String city) {
		this.city = city;
	}

	public List<WeatherData> getWeatherData() {
		return forecast;
	}

	public String getCity() {
		return city;
	}
}
