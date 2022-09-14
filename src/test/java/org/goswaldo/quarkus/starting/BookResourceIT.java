package org.goswaldo.quarkus.starting;

import io.quarkus.test.junit.QuarkusIntegrationTest;
import org.junit.jupiter.api.Test;

import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;

import static io.restassured.RestAssured.given;
import static javax.ws.rs.core.Response.Status.OK;
import static org.hamcrest.CoreMatchers.is;

@QuarkusIntegrationTest
public class BookResourceIT extends BookResourceTest {

    @Test
    void getBook_ReturnABook() {
        given()
            .header(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON)
            .pathParam("id", 1)
        .when()
            .get("/api/books/{id}")
        .then()
            .statusCode(OK.getStatusCode())
            .body("title", is("Understanding Quarkus"))
            .body("author", is("Antonio"))
            .body("genre", is("Information Technology"))
            .body("yearOfPublication", is(2020));
    }
}
