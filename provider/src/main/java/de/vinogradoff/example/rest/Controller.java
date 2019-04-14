package de.vinogradoff.example.rest;

import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@RestController
public class Controller {

  @RequestMapping(value = "/weather/now", method = RequestMethod.GET)
  public Weather weatherNow(@RequestParam(value = "city",defaultValue = "Berlin") String city){
    return new Weather(city, "125000", 29.0, new Date());
  }

}
