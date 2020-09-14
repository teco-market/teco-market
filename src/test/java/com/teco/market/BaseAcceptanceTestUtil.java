package com.teco.market;

import java.util.Objects;

import javax.xml.ws.Response;

import org.apache.http.HttpHeaders;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import io.restassured.RestAssured;
import io.restassured.http.Header;
import io.restassured.specification.RequestSpecification;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class BaseAcceptanceTestUtil {
    @LocalServerPort
    public int port;

    protected static RequestSpecification given() {
        return RestAssured.given().log().all();
    }

    @BeforeEach
    void setUp() {
        RestAssured.port = port;
    }

    protected <T> Long doPost(String path, T request, Header header) {
        String response = null;
        if (Objects.nonNull(header)) {
            response = given()
                .header(header)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .body(request)
                .when()
                .post(path)
                .then()
                .log().all()
                .statusCode(HttpStatus.CREATED.value())
                .extract().header(HttpHeaders.LOCATION);
        } else {
            response = given()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .body(request)
                .when()
                .post(path)
                .then()
                .log().all()
                .statusCode(HttpStatus.CREATED.value())
                .extract().header(HttpHeaders.LOCATION);
        }
        return Long.parseLong(response.substring(path.length() + 1));
    }

    protected <T> T doGet(String path, Header header, Class<T> response) {
        return given()
            .header(header)
            .accept(MediaType.APPLICATION_JSON_VALUE)
            .when()
            .get(path)
            .then()
            .log().all()
            .statusCode(HttpStatus.OK.value())
            .extract().as(response);
    }

    protected <T> void doPut(String path, Header header, T request) {
        given()
            .header(header)
            .body(request)
            .contentType(MediaType.APPLICATION_JSON_VALUE)
            .when()
            .put(path)
            .then()
            .log().all()
            .statusCode(HttpStatus.NO_CONTENT.value());
    }

    protected <T> void doPatch(String path, Header header, T request) {
        given()
            .header(header)
            .body(request)
            .contentType(MediaType.APPLICATION_JSON_VALUE)
            .when()
            .patch(path)
            .then()
            .log().all()
            .statusCode(HttpStatus.NO_CONTENT.value());
    }

    protected <T> void doDelete(String path, Header header) {
        given()
            .header(header)
            .when()
            .delete(path)
            .then()
            .log().all()
            .statusCode(HttpStatus.NO_CONTENT.value());
    }
}
