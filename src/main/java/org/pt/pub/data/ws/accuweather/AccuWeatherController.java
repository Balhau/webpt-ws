package org.pt.pub.data.ws.accuweather;

import java.util.Base64;

import org.pt.pub.data.sources.accuweather.AccuWeather;
import org.pt.pub.data.sources.accuweather.domain.Weather;
import org.pt.pub.data.sources.accuweather.domain.WeatherLocationList;
import org.pt.pub.data.ws.domain.WebResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/ws/weather/accu")
public class AccuWeatherController {
	private AccuWeather accuweather;
	
	public AccuWeatherController(){
		this.accuweather=new AccuWeather();
	}
	
	@RequestMapping("/locations/{location}")
	public WebResult<WeatherLocationList> getLocations(@PathVariable("location") String location){
		return WebResult.<WeatherLocationList>wrap(
				()->accuweather.getLocations(location)
		);
	}
	
	@RequestMapping("/location/{location}")
	public WebResult<Weather> getLocationWeather(@PathVariable("location") String b64location){
		return WebResult.<Weather>wrap(
				()->{
					String location=new String(Base64.getDecoder().decode(b64location.getBytes()));
					return accuweather.getLocation(location);
				}
		);
	}
}
