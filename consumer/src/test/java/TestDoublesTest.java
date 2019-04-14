import de.vinogradoff.pact101.consumer.rest.Controller;
import de.vinogradoff.pact101.consumer.rest.Weather;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.client.RestTemplate;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
public class TestDoublesTest {

  @Mock
  RestTemplate rest;

  @InjectMocks
  Controller ctrl;

  @Test
  void testCold(){
    Mockito.when(rest.getForObject("http://localhost:1234/weather/now?city=Minsk", Weather.class))
            .thenReturn(new Weather("Minsk",25.1d));
    ctrl.serviceUrl="http://localhost:1234/weather/now";


    Weather weather=  ctrl.weatherNow("Minsk");
    assertThat(weather.getCity()).isEqualTo("Minsk");
    assertThat(weather.getTemperature()).isEqualTo(25.1d);
    assertThat(weather.getHot()).isEqualTo(false);
  }

  @Test
  void testHot(){
    Mockito.when(rest.getForObject("http://localhost:1234/weather/now?city=Miami", Weather.class))
            .thenReturn(new Weather("Miami",34.3d));
    ctrl.serviceUrl="http://localhost:1234/weather/now";

    Weather weather=  ctrl.weatherNow("Miami");
    assertThat(weather.getCity()).isEqualTo("Miami");
    assertThat(weather.getTemperature()).isEqualTo(34.3d);
    assertThat(weather.getHot()).isEqualTo(true);
  }
}
