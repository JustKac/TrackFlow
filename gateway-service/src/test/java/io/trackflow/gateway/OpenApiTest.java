package io.trackflow.gateway;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.containsString;

@QuarkusTest
class OpenApiTest {

    @Test
    void openApiEndpointShouldBeAvailable() {
        given()
          .when().get("/q/openapi")
          .then()
             .statusCode(200)
             .body(containsString("openapi"));
    }

    @Test
    void swaggerUiShouldBeAvailable() {
        given()
          .when().get("/q/swagger-ui")
          .then()
             .statusCode(200);
    }
}