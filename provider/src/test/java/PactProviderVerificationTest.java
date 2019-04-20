import au.com.dius.pact.provider.junit.Provider;
import au.com.dius.pact.provider.junit.loader.PactFolder;
import au.com.dius.pact.provider.junit5.*;
import de.vinogradoff.example.rest.ApplicationProvider;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.SpringApplication;

import java.net.*;

@Provider("weather_service")
@PactFolder("../consumer/build/pact-files")
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
}


