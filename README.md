# Mini-Workshop

## Requirements:
Java 8+

On Windows use `gradlew.bat` instead of `./gradlew`

There are too projects: provider and consumer, each in it separate directory.
Start gradlew commands from inside the appropriate project.

## Exploration of Pact

### Provider
1. Start provider with `./gradlew bootRun`
2. Check that provider is running, with Postman/curl or any other API tool. Request http://localhost:8888/weather/now?city=Berlin and observe the JSON response.
3. Stop the provider (Ctrl+C)


### Consumer
1. Inspect Consumer method which call the provider: `Controller.weatherNow(...)`
2. Inspect `PactWeatherTest` class, especially `createPact()` and `unitTestCustomerMethod()`
3. Create Pact and test customer with: `./gradlew test`
4. Inspect created Pact JSON in target/pacts directory
5. Make some changes in `createPact()`, run `./gradlew test`, inspect how pact file changes
6. Make some changes to `unitTestCustomerMethod()` and/or `Controller.weatherNow()` to get the test failed/passed back.
7. Add a test method (similar to `unitTestCustomerMethod`, but calling another URL) - observe the pact test failed with explanation (Pact must describe all interactions from test)
8. Leave a single test method with empty content - observed that the test fails (Pact interaction is described, but not called/tested)
9. Make Pact test pass again

### Provider
1. Uncomment code in `build.gradle` (inspect it)
2. Run `./gradlew verifyPact` - it should fail (provider is not started)
3. Start the provider in separated session (`./gradlew bootRun`)
4. Run `./gradlew verifyPact` again - it should pass
5. Stop the provider
6. Inspect code of `PactTest` class, pay attention of configuration annotations
7. Run `./gradlew test` - it should pass (starting the provider before the test)
8. Change configuration annotations values (folder, provider name)  to fail the test, observe the error messages.
9. Return everything back to passing test

### Provider
Note: You can verify with either `./gradlew clean verifyPact` or with `./gradlew clean test`. When test fails, look for explaining messages in gradle HTML reports.
1. Change format of Provider response - in class `Weather` rename `zipCode` property/methods
2. Verify pact - it must be passing (change doesn't make pact invalid)
3. Change format of Provider response - in class `Weather` rename `city` property/methods
4. Verify pact - it must be failing  (change made pact invalid)
5. Consumer: make changes to pact, rerunning the test (to generate new pact json)
6. Inspect which effect does it have to pact verification by provider


Feel free to change code on both side, observing the effects.
