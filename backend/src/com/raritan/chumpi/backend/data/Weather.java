package com.raritan.chumpi.backend.data;

public class Weather {

	private double temp;
	private double pressure;
	private int humidity;

	public void setTemp(double temp) {
		this.temp = temp;
	}

	public void setPressure(double pressure) {
		this.pressure = pressure;
	}

	public void setHumidity(int humidity) {
		this.humidity = humidity;
	}

	public double getTemp() {
		return temp;
	}

	public double getPressure() {
		return pressure;
	}

	public int getHumidity() {
		return humidity;
	}

}
