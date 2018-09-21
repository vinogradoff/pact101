import au.com.dius.pact.consumer.*;
import au.com.dius.pact.consumer.dsl.*;
import au.com.dius.pact.consumer.junit5.*;
import au.com.dius.pact.model.RequestResponsePact;
import de.vinogradoff.pact101.consumer.rest.*;
import org.apache.commons.collections4.MapUtils;
import org.apache.http.HttpResponse;
import org.apache.http.client.fluent.Request;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import java.io.IOException;
import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(PactConsumerTestExt.class)
@PactTestFor(providerName = "weather_service", port = "1234")
public class PactWeatherTest {

  Map<String, String> headers = MapUtils.putAll(new HashMap<String, String>(),
          new String[]{"Content-Type", "application/json"});

  @Pact(provider="weather_service", consumer="weather_portal")
  public RequestResponsePact createPact(PactDslWithProvider builder) {
    return builder
            .uponReceiving("ExampleJavaConsumerPactTest test interaction")
            .path("/weather/now")
            .method("GET")
            .matchQuery("city", ".*", "Moscow")
            .willRespondWith()
            .headers(headers)
            .status(200)
            .body( new PactDslJsonBody()
                    .stringType("city", "Moscow")
                    .decimalType("temperature", 29.0d)
                    //.decimalType("money", 25d)
            )
            .toPact();
  }


  /* this just creates a pact file */
  //@Test
  void runMock(MockServer mockServer) throws IOException {
    HttpResponse httpResponse = Request.Get(mockServer.getUrl() + "/weather/now?city=London").execute().returnResponse();
    assertThat(httpResponse.getStatusLine().getStatusCode()).isEqualTo(200);
  }


  @Test
  void unitTestCustomerMethod(MockServer mockServer) throws IOException {
    Controller ctrl=new Controller();
    ctrl.serviceUrl=mockServer.getUrl()+"/weather/now";
    Weather weather=  ctrl.weatherNow("Moscow2");
    assertThat(weather.getCity()).isEqualTo("Moscow");
    assertThat(weather.getTemperature()).isGreaterThan(0);
    assertThat(weather.getHot()).isFalse();
  }

}
