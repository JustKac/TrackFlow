package io.trackflow.gateway;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.is;

@QuarkusTest
class ObservabilityTest {

    @Test
    void healthEndpointShouldBeAvailable() {
        given()
          .when().get("/q/health")
          .then()
             .statusCode(200)
             .body("status", is("UP"));
    }

    @Test
    void metricsEndpointShouldBeAvailable() {
        given()
          .when().get("/q/metrics")
          .then()
             .statusCode(200)
             .body(containsString("# HELP"));
    }
}