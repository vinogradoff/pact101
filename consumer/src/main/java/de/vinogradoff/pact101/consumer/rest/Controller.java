package de.vinogradoff.pact101.consumer.rest;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.Date;

@RestController
public class Controller {

  @Value("${weather.service}")
  public String serviceUrl;

  @RequestMapping(value = "/newsportal/getWeather", method = RequestMethod.GET)
  public Weather weatherNow(@RequestParam(value = "city",defaultValue = "Moscow") String city){
    Weather weather= new RestTemplate().getForObject(serviceUrl+"?city="+city,Weather.class);
    if (weather.getTemperature()>=30) weather.setHot(true); else weather.setHot(false);
    return weather;
  }



}
