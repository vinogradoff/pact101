
import de.vinogradoff.pact101.consumer.rest.*;
import org.junit.jupiter.api.*;

import static io.restassured.RestAssured.get;
import static org.assertj.core.api.Assertions.*;

public class IntegrationTest {

 @Test
  void weatherPortalShouldReturn(){
  Weather weather=get("http://localhost:8080/newsportal/getWeather?city=Minsk")
          .then()
          .statusCode(200)
          .extract().as(Weather.class);

  assertThat(weather.getCity()).isEqualTo("Minsk");
  assertThat(weather.getTemperature()).isGreaterThan(0);

 }


 @Test
 void providerAPIshouldReturn(){
  Controller ctrl=new Controller();
  ctrl.serviceUrl="http://localhost:8888/weather/now";
  Weather weather=  ctrl.weatherNow("Moscow");
  assertThat(weather.getCity()).isEqualTo("Moscow");
  assertThat(weather.getTemperature()).isGreaterThan(0);
 }

}
