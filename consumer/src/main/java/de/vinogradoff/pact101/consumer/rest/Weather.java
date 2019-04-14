package de.vinogradoff.pact101.consumer.rest;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.Date;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Weather {

  private String city;
  private Double temperature;
  private Boolean hot;

  public Boolean getHot() {
    return hot;
  }

  public void setHot(Boolean hot) {
    this.hot = hot;
  }

  public Weather() {

  }

  public Weather(String city, Double temperature){
    this.city=city;
    this.temperature=temperature;
  }
  public Double getTemperature() {
    return temperature;
  }

  public void setTemperature(Double temperature) {
    this.temperature = temperature;
  }

  public String getCity() {

    return city;
  }

  public void setCity(String city) {
    this.city = city;
  }
}
