package io.github.drr00t.sakila.control;

import io.github.drr00t.sakila.boundary.AddActor;
import io.github.drr00t.sakila.boundary.Result;
import io.github.drr00t.sakila.entity.ActorId;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

@QuarkusTest
public class CatalogManagementTest {

    @Inject
    CatalogManagement catalogManagement;

    @Test
    void shouldAddNewActorSuccessfully() {
        // Arrange
//        CatalogManagement actorBusinessOperation = new CatalogManagement();
        AddActor actor = new AddActor("John", "Doe");

        // Act
        Result<ActorId> result = catalogManagement.createActor(actor);

        // Assert
        Assertions.assertTrue(result.isValid());
    }

//    @Test
//    void shouldReturnErrorWhenActorAlreadyExists() {
//        // Arrange
////        CatalogManagement catalogManagement = new CatalogManagement();
//        AddActor actor = new AddActor("John", "Doe");
//        catalogManagement.createActor(actor);
//
//        // Act
//        Result<ActorId> result = catalogManagement.createActor(actor);
//
//        // Assert
//        Assertions.assertFalse(result.isValid());
//        Assertions.assertEquals(List.of("Actor already exists"), result.errorMessages());
//    }

//    @Test
//    void shouldTreatActorsWithDifferentCasingAsSame() {
//        // Arrange
////        CatalogManagement catalogManagement = new CatalogManagement();
//        AddActor actor1 = new AddActor("John", "Doe");
//        AddActor actor2 = new AddActor("john", "doe");
//
//        // Act
//        Result<ActorId> result1 = catalogManagement.createActor(actor1);
//        Result<ActorId> result = catalogManagement.createActor(actor2);
//
//        // Assert
//        Assertions.assertFalse(result.isValid());
//        Assertions.assertEquals(List.of("Actor already exists"), result.errorMessages());
//    }

}