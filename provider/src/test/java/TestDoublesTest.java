import de.vinogradoff.example.rest.Controller;
import de.vinogradoff.example.rest.Weather;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class TestDoublesTest {

  @Test
  void unitTest(){
    Weather weather=new Controller().weatherNow("Cairo");
    assertThat(weather.getCity()).isEqualTo("Cairo");
    assertThat(weather.getTemperature()).isEqualTo(29d);
    assertThat(weather.getZipCode()).isNotBlank();
    assertThat(weather.getDate()).isNotNull();
  }
}
