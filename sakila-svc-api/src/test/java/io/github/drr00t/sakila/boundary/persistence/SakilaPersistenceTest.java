package io.github.drr00t.sakila.boundary.persistence;

import io.github.drr00t.sakila.boundary.CatalogRepository;
import io.github.drr00t.sakila.boundary.Result;
import io.github.drr00t.sakila.entity.Actor;
import io.github.drr00t.sakila.entity.ActorId;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.persistence.PersistenceException;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.wildfly.common.Assert;

import java.time.Instant;

@Transactional
@QuarkusTest
public class SakilaPersistenceTest {


    @Test
    void actorIdShouldBeGeneratedAutomatically() {
        ActorRecord actor = new ActorRecord();
        actor.firstName = "John";
        actor.lastName = "Doe";
        actor.lastUpdate = Instant.now();
        actor.persist();

        Assert.assertNotNull(actor.id);
    }

    @Test
    void firstNameShouldNotBeNull() {
        ActorRecord actor = new ActorRecord();
        actor.lastName = "Doe";
        actor.lastUpdate = Instant.now();

        Assertions.assertThrows(PersistenceException.class, actor::persist);
    }

    @Test
    void lastNameShouldNotBeNull() {
        ActorRecord actor = new ActorRecord();
        actor.firstName = "John";
        actor.lastUpdate = Instant.now();

        Assertions.assertThrows(PersistenceException.class, actor::persist);
    }

    @Test
    void lastUpdateShouldBeSetAutomatically() {
        ActorRecord actor = new ActorRecord();
        actor.firstName = "Jane";
        actor.lastName = "Smith";
        actor.persist();

        Assert.assertNotNull(actor.lastUpdate);
    }

    @Test
    void catalogRepositoryShouldPersistActor() {
        var actor = Actor.newActor("John", "Doe");

        CatalogRepository catalogRepository = new CatalogRepository();
        Result<ActorId> result = catalogRepository.save(actor);
        Result<Actor> found = catalogRepository.findById(result.entity());

        Assert.assertTrue(result.isValid());
        Assertions.assertEquals(actor.getFirstName(), found.entity().getFirstName());
        Assertions.assertEquals(actor.getLastName(), found.entity().getLastName());
    }

    @Test
    void catalogRepositoryShouldUpdateActor() {
        var actor = Actor.newActor("John Update", "Doe Update");
        CatalogRepository catalogRepository = new CatalogRepository();
        Result<ActorId> result = catalogRepository.save(actor);

        actor.setFirstName("Jane Update");
        actor.setLastName("Smith Update");

        Result<ActorId> updateResult = catalogRepository.update(actor);
        Result<Actor> found = catalogRepository.findById(updateResult.entity());

        Assert.assertNotNull(updateResult.isValid());
        Assertions.assertEquals("Jane Update", found.entity().getFirstName());
        Assertions.assertEquals("Smith Update", found.entity().getLastName());
    }

    @Test
    void catalogRepositoryShouldDeleteActor() {
        var actor = Actor.newActor("John Delete", "Doe Delete");
        CatalogRepository catalogRepository = new CatalogRepository();
        Result<ActorId> result = catalogRepository.save(actor);

        Result<ActorId> deleteResult = catalogRepository.delete(result.entity());
        Result<Actor> found = catalogRepository.findById(result.entity());

        Assert.assertNotNull(deleteResult.isValid());
        Assert.assertFalse(found.isValid());
    }


}
