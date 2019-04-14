package de.vinogradoff.example.rest;

import java.util.Date;

public class Weather {

  public Weather(){}

  private String city;
  private String zipCode;
  private Double temperature;
  private Date date;

  public Weather(String city, String zipCode, Double temperature, Date date) {
    this.city = city;
    this.zipCode = zipCode;
    this.temperature = temperature;
    this.date = date;
  }


  public String getZipCode() {
    return zipCode;
  }

  public void setZipCode(String zipCode) {
    this.zipCode = zipCode;
  }

  public Double getTemperature() {
    return temperature;
  }

  public void setTemperature(Double temperature) {
    this.temperature = temperature;
  }

  public Date getDate() {
    return date;
  }

  public void setDate(Date date) {
    this.date = date;
  }

  public String getCity() {
    return city;
  }

  public void setCity(String city) {
    this.city = city;
  }
}
