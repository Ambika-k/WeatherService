package com.spring.restapi.controllers;
/**
 * @author Vijaya Bramarambika
 *August30, 2022
 * 
 */

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.spring.restapi.models.FormCityAttribute;
import com.spring.restapi.models.SmsRequest;
import com.spring.restapi.models.Weather;
import com.spring.restapi.models.WeatherUrl;
import com.spring.restapi.restclient.SmsRestClient;

@ComponentScan("com.spring.restapi.config")
@Controller
public class WeatherController {

	@Autowired
	RestTemplate restTemp;
	@Autowired
	private SmsRestClient restClient;

	@Autowired
	private WeatherUrl weatherData;

	// private String weatherMsg;
	private Weather weatherMsg;

	@RequestMapping(value = "/weather", method = RequestMethod.GET)
	public String CityForm(Model model) {

		model.addAttribute("city", new FormCityAttribute());
		return "formData";
	}

	@RequestMapping(value = "/weather", method = RequestMethod.POST)
	public String getWeather(Model model, @ModelAttribute FormCityAttribute city)
			throws JsonParseException, JsonMappingException, IOException {

		UriComponents uriComponents = UriComponentsBuilder.newInstance().scheme("http").host(weatherData.getUrl())
				.path("").query("q={keyword}&appid={appid}").buildAndExpand(city.getCity(), weatherData.getApiKey());

		String uri = uriComponents.toUriString();

		ResponseEntity<String> resp = restTemp.exchange(uri, HttpMethod.GET, null, String.class);

		ObjectMapper mapper = new ObjectMapper();
		Weather weather = mapper.readValue(resp.getBody(), Weather.class);
		model.addAttribute("weatherData", weather);
		// String weatherMessage = weather.toString();
		// setWeatherMessage(weatherMessage);
		setWeatherMessage(weather);
		return "weatherDetails";
	}

	public void setWeatherMessage(/* String */Weather weatherMessage) {
		weatherMsg = weatherMessage;
		// weathermsg = weather1;
		// System.out.println(getWeatherMessage());
		System.out.println("called set");
	}

	@ResponseBody
	@GetMapping("/getweather")
	// @RequestMapping(value = "/getweather", method = RequestMethod.GET,produces =
	// {
	// MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE },consumes
	// = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE} )
	public Weather getWeatherMessage() {
		System.out.println("called get");
		System.out.println(weatherMsg);
		return weatherMsg;
	}

	// @PostMapping("/sendmessage")
	@ResponseBody
	@RequestMapping(value = "/sendmessage", method = RequestMethod.POST, produces = {
			MediaType.APPLICATION_JSON_VALUE }, consumes = { MediaType.APPLICATION_FORM_URLENCODED_VALUE,
					MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<Object> sendMsg(SmsRequest smsRequest) {
		System.out.println(smsRequest.getNumber());
		String number = smsRequest.getNumber();
		String status = restClient.sendMessage(number);
		System.out.println("hello check\n");
		// restTemp.exchange("http://SMSSERVICE/sendmessage"+smsRequest,
		// HttpMethod.POST, null, Object.class);
		// restTemp.postForObject("http://SMSSERVICE/sendmessage"+smsRequest,
		// null,Object.class);
		if ("sent".equals(status) || "queued".equals(status)) {
			System.out.println("sent");
			return new ResponseEntity<Object>("Sms Sent succesfully", HttpStatus.OK);
		}
		return new ResponseEntity<Object>("Failed to send message", HttpStatus.NOT_FOUND);

	}
}