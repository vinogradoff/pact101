package de.vinogradoff.pact101.consumer.rest;

public class City {

  private String city;

  public String getCity() {
    return city;
  }

  public void setCity(String city) {
    this.city = city;
  }

  public City(String city, String zip) {
    this.city = city;
  }
}
