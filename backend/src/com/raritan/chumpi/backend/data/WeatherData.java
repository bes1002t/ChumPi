package com.raritan.chumpi.backend.data;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class WeatherData {

	private Date date;

	// Temperatur in Celsius
	private double temp;
	// Atmospheric pressure on the sea level in hPa
	private double pressure;
	// Humidity in %
	private int humidity;
	// Cloudiness in %
	private int cloudiness;
	// Wind speed in meter/sec
	private double windSpeed;
	// Wind direction in degrees
	private double windDirec;
	// Rain volume for last 3 hours in mm
	private double rain;
	// Snow volume for last 3 hours in mm
	private double snow;

	private List<WeatherDetails> details = new ArrayList<WeatherDetails>();


	public void setDate(Date date) {
		this.date = date;
	}

	public void setTemp(double temp) {
		this.temp = temp;
	}

	public void setPressure(double pressure) {
		this.pressure = pressure;
	}

	public void setHumidity(int humidity) {
		this.humidity = humidity;
	}

	public void setCloudiness(int cloudiness) {
		this.cloudiness = cloudiness;
	}

	public void setWindSpeed(double windSpeed) {
		this.windSpeed = windSpeed;
	}

	public void setWindDirection(double windDirec) {
		this.windDirec = windDirec;
	}

	public void setRainVolume(double rain) {
		this.rain = rain;
	}

	public void setSnowVolume(double snow) {
		this.snow = snow;
	}

	public void addDetails(WeatherDetails details) {
		this.details.add(details);
	}

	public Date getDate() {
		return date;
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

	public int getCloudiness() {
		return cloudiness;
	}

	public double getWindSpeed() {
		return windSpeed;
	}

	public double getWindDirection() {
		return windDirec;
	}

	public double getRainVolume() {
		return rain;
	}

	public double getSnowVolume() {
		return snow;
	}

	public List<WeatherDetails> getDetails() {
		return details;
	}

	@Override
	public String toString() {
		String className = this.getClass().getSimpleName();
		return String.format("%s(date=%s, temp=%f, pressure=%f, humidity=%d, cloudiness=%d, windSpeed=%f, windDirec=%f, rain=%f, snow=%f, details=%s)",
			   className, date.toString(), temp, pressure, humidity, cloudiness, windSpeed, windDirec, rain, snow, details);
	}
}