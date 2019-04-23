import au.com.dius.pact.provider.junit.*;
import au.com.dius.pact.provider.junit.loader.*;
import au.com.dius.pact.provider.junit5.*;
import de.vinogradoff.example.rest.ApplicationProvider;
import de.vinogradoff.example.rest.Controller;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.SpringApplication;

import java.net.*;

@Provider("weather_service")
//@PactFolder("../consumer/build/pact-files")
@PactBroker(host = "comaqa.pact.dius.com.au",port = "443",scheme = "https",
        authentication = @PactBrokerAuth(username = "70k2cqi8e7nh3ksd9pcg8e",
                password = "3w986o6yb87t4gw10vtfko"))
public class PactProviderVerificationTest {

  @TestTemplate
  @ExtendWith(PactVerificationInvocationContextProvider.class)
  void pactVerificationTestTemplate(PactVerificationContext context) {
    context.verifyInteraction();
  }

  @BeforeAll
  static void setUpService() {
    //Run DB, create schema
    //Run service
    //...
    SpringApplication.run(ApplicationProvider.class, new String[]{});
  }

  @BeforeEach
  void before(PactVerificationContext context) throws MalformedURLException {
    context.setTarget(HttpTestTarget.fromUrl(new URL("http://localhost:8888")));
    // or something like
    // context.setTarget(new HttpTestTarget("localhost", myProviderPort, "/"));
  }

  @State("Sensors are functioning")
  void setupApplicationOK(){
    //Controller.offline=false;
  }

  @State("Sensors are down")
  void setupApplication503(){
    Controller.offline=true;
  }

  @State(value = "Sensors are down", action = StateChangeAction.TEARDOWN)
  void cleanUp503(){
    Controller.offline=false;
  }
}


