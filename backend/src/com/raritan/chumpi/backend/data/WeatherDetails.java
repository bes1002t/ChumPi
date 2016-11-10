package com.raritan.chumpi.backend.data;
public class WeatherDetails {

	private String name;
	private String descr;

	public void setName(String name) {
		this.name = name;
	}

	public void setDescription(String descr) {
		this.descr = descr;
	}

	public String getName() {
		return name;
	}

	public String getDescription() {
		return descr;
	}
}