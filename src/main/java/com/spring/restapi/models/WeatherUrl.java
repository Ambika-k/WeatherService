/**
 * 
 */
package com.spring.restapi.models;

/**
 * @author Vijaya Bramarambika
 *August30, 2022
 * 
 */
public class WeatherUrl {

	private String url;
	private String apiKey;
	
	public WeatherUrl() {
		super();
		// TODO Auto-generated constructor stub
	}

	public String getApiKey() {
		return apiKey;
	}

	public void setApiKey(String apiKey) {
		this.apiKey = apiKey;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}


}
