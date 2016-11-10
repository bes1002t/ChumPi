package com.raritan.chumpi.backend.rest.serialization;

import java.lang.reflect.Type;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.raritan.chumpi.backend.data.Weather;
import com.raritan.chumpi.backend.data.WeatherData;
import com.raritan.chumpi.backend.data.WeatherDetails;

public class WeatherDeserializer implements JsonDeserializer<Weather> {

	private final static String ATTR_CITY = "city";
		private final static String ATTR_NAME = "name";

	private final static String ATTR_LIST = "list";
		private final static String ATTR_DATE = "dt_txt";
		private final static String ATTR_MAIN = "main";
			private final static String ATTR_TEMP = "temp";
			private final static String ATTR_PRESSURE = "pressure";
			private final static String ATTR_HUMIDITY = "humidity";
		private final static String ATTR_DETAILS = "weather";
			private final static String ATTR_DETAIL_NAME = "main";
			private final static String ATTR_DETAIL_DESCR = "description";
		private final static String ATTR_CLOUDS = "clouds";
			private final static String ATTR_CLOUDS_DETAIL = "all";
		private final static String ATTR_WIND = "wind";
			private final static String ATTR_WIND_SPEED = "speed";
			private final static String ATTR_WIND_DIREC = "deg";
		private final static String ATTR_RAIN = "rain";
			private final static String ATTR_RAIN_DETAIL = "3h";
		private final static String ATTR_SNOW = "snow";
			private final static String ATTR_SNOW_DETAIL = "3h";


	@Override
	public Weather deserialize(JsonElement jsonElem, Type type, JsonDeserializationContext ctx) throws JsonParseException {
		return getWeatherObject(jsonElem);
	}

	private Weather getWeatherObject(JsonElement elem) {
		Weather weather = new Weather();
		String cityName = getStringVal(elem, ATTR_CITY, ATTR_NAME);
		weather.setCity(cityName);

		JsonArray jsonWeatherList = getVal(elem, ATTR_LIST).getAsJsonArray();
		for (JsonElement child : jsonWeatherList) {
			WeatherData data = getWeatherData(child);
			weather.addWeatherData(data);
		}

		return weather;
	}

	private WeatherData getWeatherData(JsonElement elem) {
		String dateStr = getStringVal(elem, ATTR_DATE);
		Integer cloudiness = getIntVal(elem, ATTR_CLOUDS, ATTR_CLOUDS_DETAIL);
		double windSpeed = getDoubleVal(elem, ATTR_WIND, ATTR_WIND_SPEED);
		double windDirec = getDoubleVal(elem, ATTR_WIND, ATTR_WIND_DIREC);
		double rain = getDoubleVal(elem, ATTR_RAIN, ATTR_RAIN_DETAIL);
		double snow = getDoubleVal(elem, ATTR_SNOW, ATTR_SNOW_DETAIL);

		JsonElement jsonWeatherData = getVal(elem, ATTR_MAIN);
		double temp = getDoubleVal(jsonWeatherData, ATTR_TEMP);
		double pressure = getDoubleVal(jsonWeatherData, ATTR_PRESSURE);
		Integer humidity = getIntVal(jsonWeatherData, ATTR_HUMIDITY);

		WeatherData data = new WeatherData();

		try {
			DateFormat df = new SimpleDateFormat("yyyy-MM-dd kk:mm:ss");
			Date date = df.parse(dateStr);
			data.setDate(date);
		} catch (ParseException e) {
			e.printStackTrace();
		}

		data.setTemp(temp);
		data.setPressure(pressure);
		data.setHumidity(humidity);
		data.setCloudiness(cloudiness);
		data.setWindSpeed(windSpeed);
		data.setWindDirection(windDirec);
		data.setRainVolume(rain);
		data.setSnowVolume(snow);

		JsonArray jsonWeatherDetails = getVal(elem, ATTR_DETAILS).getAsJsonArray();
		for (JsonElement child : jsonWeatherDetails) {
			WeatherDetails details = getWeatherDetails(child.getAsJsonObject());
			data.addDetails(details);
		}

		return data;
	}

	private WeatherDetails getWeatherDetails(JsonObject obj) {
		String detailName = getStringVal(obj, ATTR_DETAIL_NAME);
		String detailDescr = getStringVal(obj, ATTR_DETAIL_DESCR);

		WeatherDetails details = new WeatherDetails();
		details.setName(detailName);
		details.setDescription(detailDescr);

		return details;
	}

	private double getDoubleVal(JsonElement parent, String... keys) {
		JsonElement elem = getVal(parent, keys);

		if (elem == null) return -1.00;
		else return elem.getAsDouble();
	}

	private int getIntVal(JsonElement parent, String... keys) {
		JsonElement elem = getVal(parent, keys);

		if (elem == null) return -1;
		else return elem.getAsInt();
	}

	private String getStringVal(JsonElement parent, String... keys) {
		JsonElement elem = getVal(parent, keys);

		if (elem == null) return null;
		else return elem.getAsString();
	}

	private JsonElement getVal(JsonElement parent, String... keys) {
		JsonElement elem = parent.getAsJsonObject().get(keys[0]);

		List<String> newKeys = new ArrayList<String>(Arrays.asList(keys));
		newKeys.remove(0);

		if (elem != null && elem.isJsonObject() && keys.length > 1) return getVal(elem, newKeys.toArray(new String[0]));
		else return elem;
	}
}
