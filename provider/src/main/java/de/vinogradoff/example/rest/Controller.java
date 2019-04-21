package de.vinogradoff.example.rest;

import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@RestController
public class Controller {

  public static Boolean offline=false; // just for example's sake - it is not a good pattern


  @RequestMapping(value = "/weather/now", method = RequestMethod.GET)
  public Weather weatherNow(@RequestParam(value = "city",defaultValue = "Berlin") String city){
    if (offline){throw new SensorsNotWorkingException();}
    return new Weather(city, "125000", 29.0, new Date());
  }

  @ResponseStatus(HttpStatus.SERVICE_UNAVAILABLE)
  public static class SensorsNotWorkingException extends RuntimeException {}

}
