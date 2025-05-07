package io.github.drr00t.sakila.entity;

import io.github.drr00t.sakila.control.validation.ValidationRule;
import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import java.time.Instant;

import static org.junit.jupiter.api.Assertions.*;

@QuarkusTest
class ActorTest {

    @Test
    void testParameterizedConstructor() {
        ActorId actorId = ActorId.of(10);
        String firstName = "John";
        String lastName = "Doe";
        Instant lastUpdate = Instant.now();

        Actor actor = new Actor(actorId, firstName, lastName, lastUpdate);

        assertEquals(actorId, actor.getIdentity());
        assertEquals(firstName, actor.getFirstName());
        assertEquals(lastName, actor.getLastName());
        assertEquals(lastUpdate, actor.getLastUpdate());
    }

    @Test
    void testActorEquals() {
        var instant = Instant.now();
        var actorId = ActorId.of(10);
        Actor actor1 = new Actor(actorId, "John", "Doe", instant);
        Actor actor2 = new Actor(actorId, "John", "Doe", instant);

        assertEquals(actor1, actor2);
    }

    @Test
    void testActorNotEquals() {
        var actorId1 = ActorId.of(10);
        var actorId2 = ActorId.of(20);
        Actor actor1 = new Actor(actorId1, "John", "Doe", Instant.now());
        Actor actor2 = new Actor(actorId2, "Jane", "Doe", Instant.now());

        assertNotEquals(actor1, actor2);
    }

    @Test
    void testNewActor() {
        String firstName = "Jane";
        String lastName = "Smith";

        Actor actor = Actor.newActor(firstName, lastName);

        assertNotNull(actor);
        assertEquals(firstName, actor.getFirstName());
        assertEquals(lastName, actor.getLastName());
        assertNotNull(actor.getIdentity());
        assertNotNull(actor.getLastUpdate());
    }

    @Test
    void testUpdateActor() {
        var actorId = ActorId.of(10);
        Actor actor = new Actor(actorId, "John", "Doe", Instant.now());
        String newFirstName = "Jane";
        String newLastName = "Smith";

        Actor updatedActor = Actor.Update(actor, newFirstName, newLastName);

        assertEquals(actorId, updatedActor.getIdentity());
        assertEquals(newFirstName, updatedActor.getFirstName());
        assertEquals(newLastName, updatedActor.getLastName());
        assertNotEquals(actor.getLastUpdate(), updatedActor.getLastUpdate());
    }

    @Test
    void testUpdateFirstName() {
        var actorId = ActorId.of(10);
        Actor actor = new Actor(actorId, "John", "Doe", Instant.now());
        String newFirstName = "Jane";

        actor.setFirstName(newFirstName);

        assertEquals(newFirstName, actor.getFirstName());
    }

    @Test
    void testActorCreated() {
        String firstName = "John";
        String lastName = "Doe";

        Actor newActor = Actor.newActor(firstName, lastName);

        assertEquals(firstName, newActor.getFirstName());
        assertEquals(lastName, newActor.getLastName());
        assertEquals(1, newActor.getEvents().size());
    }

    @Test
    void testActorUpdated() {
        var actorId = ActorId.of(10);
        Actor actor = new Actor(actorId, "John", "Doe", Instant.now());

        String newFirstName = "Jane";
        String newLastName = "Foster";
        var updatedActor = Actor.Update(actor, newFirstName, newLastName);

        assertEquals(newFirstName, updatedActor.getFirstName());
        assertEquals(newLastName, updatedActor.getLastName());
        assertEquals(1, updatedActor.getEvents().size());
    }

    @Test
    void testFirstNameOk() {
        var actorId = ActorId.of(10);
        String firstName = "John";
        Actor actor = new Actor(actorId, "John", "Doe", Instant.now());
        var validateRule = new ValidationRule<Actor>();
        var result = validateRule.validate(actor);
        assertTrue(result.isValid());
    }

    @Test
    void testFirstNameNotOk() {
        var actorId = ActorId.of(10);
        String firstName = "JohnJacobJingleheimerSchmidtTheLongestNameEverWritten";
        Actor actor = new Actor(actorId, firstName, "Doe", Instant.now());
        var validateRule = new ValidationRule<Actor>();
        var result = validateRule.validate(actor);
        assertFalse(result.isValid());
        assertEquals(1, result.errorMessages().size());
    }
}