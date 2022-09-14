package com.spring.restapi.models;

/**
 * @author Vijaya Bramarambika
 *August30, 2022
 * 
 */

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.util.List;
import java.util.Map;

import org.springframework.context.annotation.Bean;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Weather implements Serializable {

	private static final long serialVersionUID = 7406210628182440902L;

	private String weatherDescription;
	private double lon;
	private String name;
	private double lat;
	private double temp;
	// private LocalDateTime date;

	@Bean
	public Weather weather() {
		return new Weather();
	}

	public Weather() {
		super();
		System.out.println("called");
		// TODO Auto-generated constructor stub
	}

	public Weather(Weather weather) {
		weatherDescription = weather.weatherDescription;
		lon = weather.lon;
		name = weather.name;
		lat = weather.lat;
		temp = weather.temp;
	}

	public double getLat() {
		return lat;
	}

	@JsonProperty("lat")
	public void setLat(double lat) {
		String alt = String.format("%4.2f", lat);
		this.lat = Double.parseDouble(alt);
	}

	public String getName() {
		return name;
	}

	@JsonProperty("name")
	public void setName(String name) {
		this.name = name;
	}

	public String getWeatherDescription() {
		return weatherDescription;
	}

	public void setWeatherDescription(String weatherDescription) {
		this.weatherDescription = weatherDescription;
	}

	public double getTemp() {
		String alt = String.format("%5.2f", temp - 273.15);
		return Double.parseDouble(alt);
	}

	@JsonProperty("temp")
	public void setTemp(double temp) {
		 // converted temperature into celsius and set the format
		this.temp = temp; // converted string to double
	}

	@JsonProperty("weather")
	public void setWeather(List<Map<String, Object>> weatherEntries) {
		Map<String, Object> weather = weatherEntries.get(0);
		setWeatherDescription((String) weather.get("description"));
	}

	@JsonProperty("lon")
	public double getLon() {
		return lon;
	}

	@JsonProperty("lon")
	public void setLon(double lon) {
		String alt = String.format("%5.2f", lon);
		this.lon = Double.parseDouble(alt);
	}

	@JsonProperty("coord")
	public void setCoord(Map<String, Object> coord) {
		setLon((double) coord.get("lon"));
		setLat((double) coord.get("lat"));

	}

	// Set the temperature
	@JsonProperty("main")
	public void setMain(Map<String, Object> main) {
		setTemp((double) main.get("temp")); // retrieved temperature from WeatherAPI
	}

	public LocalDate getDate() { // System.out.println(date);
		return LocalDate.now();
	}

	public String getTime() {
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HH:mm:ss");
		LocalTime localTime = LocalTime.now(); // System.out.println(dtf.format(localTime));
		return dtf.format(localTime);
	}

	@Override
	public String toString() {
		return "\nCity=" + name + "\nweatherDescription=" + weatherDescription + "\nLongitude=" + lon  + "\nLatitue=" + lat
				+ "\nTemperature=" + temp + " Celsius";
		// , date=" + date + " to be added
	}
}
