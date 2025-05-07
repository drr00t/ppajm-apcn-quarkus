package io.github.drr00t.sakila.boundary;

import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;

@QuarkusTest
public class RestApisTest {
    @Test
    public void testAddActorOK() {
        AddActor actor = new AddActor("New Film", "New Description");

        given()
                .contentType(ContentType.JSON)
                .body(actor)
                .when().post("/api/catalog/actors")
                .then()
                .statusCode(201);
    }

    @Test
    public void testGetAllActorsOK() {
        given()
                .contentType(ContentType.JSON)
                .when().get("/api/catalog/actors")
                .then()
                .statusCode(200);
    }

//    @Test
//    public void testAvoidDuplicateActors() {
//        AddActor actor = new AddActor("New Film Duplicated", "New Description");
//
//        given()
//                .contentType(ContentType.JSON)
//                .body(actor)
//                .when().post("/api/catalog/actors")
//                .then()
//                .statusCode(201);
//
//        given()
//                .contentType(ContentType.JSON)
//                .body(actor)
//                .when().post("/api/catalog/actors")
//                .then()
//                .statusCode(400);
//    }
}
