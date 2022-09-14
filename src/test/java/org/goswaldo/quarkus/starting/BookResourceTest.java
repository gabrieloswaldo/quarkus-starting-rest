package org.goswaldo.quarkus.starting;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;

import static io.restassured.RestAssured.given;
import static javax.ws.rs.core.Response.Status.OK;
import static org.hamcrest.CoreMatchers.is;

@QuarkusTest
class BookResourceTest {

    @Test
    void getAllBooks_ReturnListOfBooks() {
        given()
            .header(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON)
        .when()
            .get("/api/books")
        .then()
            .statusCode(OK.getStatusCode())
            .body("size()", is(4));
    }

    @Test
    void countAllBooks_ReturnNumberOfBooks() {
        given()
            .header(HttpHeaders.ACCEPT, MediaType.TEXT_PLAIN)
        .when()
            .get("/api/books/count")
        .then()
            .statusCode(OK.getStatusCode())
            .body(is("4"));
    }

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
            .body("genre", is("IT"))
            .body("yearOfPublication", is(2020));
    }

}