package com.github.drr00t;

import org.junit.jupiter.api.Test;

import com.github.drr00t.boundery.dto.AddFilm;

import io.quarkus.test.junit.QuarkusTest;

import static io.restassured.RestAssured.given;

@QuarkusTest
public class FilmResourceTest {

    // private FilmService filmService;

    // @BeforeEach
    // void setUp() {
    // filmService = new FilmService();
    // }

    // @Test
    // void testCreateFilm() {
    // // Arrange
    // var film = new AddFilm(
    // "Test Film",
    // 2023,
    // "Description",
    // 1,
    // 1,
    // 60,
    // 5.0,
    // 2,
    // 10.0,
    // "PG-13",
    // "Special Features");

    // // Act
    // filmService.createFilm(film);
    // assertNotNull(film);
    // }

    @Test
    void testCreateFilmRoute() {
        // Arrange
        var film = new AddFilm(
                "Test Film",
                2023,
                "Description",
                1,
                1,
                60,
                5.0,
                2,
                10.0,
                "PG-13",
                "Special Features");

        // Act & Assert
        given()
                .contentType("application/json")
                .body(film)
                .when().post("/films")
                .then()
                .statusCode(201);
    }

}
