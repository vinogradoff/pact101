import de.vinogradoff.example.rest.Weather;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.get;
import static org.assertj.core.api.Assertions.assertThat;

public class IntegrationTest {

 @Test
  void weatherServiceShouldReturn(){
  Weather weather=get("http://localhost:8888/weather/now?city=Minsk")
          .then()
          .statusCode(200)
          .extract().as(Weather.class);

  assertThat(weather.getCity()).isEqualTo("Minsk");
  assertThat(weather.getTemperature()).isEqualTo(29);
  assertThat(weather.getDate()).isNotNull();
  assertThat(weather.getZipCode()).isNotBlank();

 }




}
