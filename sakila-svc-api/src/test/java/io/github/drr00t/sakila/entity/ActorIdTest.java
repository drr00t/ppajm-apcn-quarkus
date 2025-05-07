package io.github.drr00t.sakila.entity;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

@QuarkusTest
public class ActorIdTest {

    @Test
    void equalsShouldReturnTrueForSameInstance() {
        int id = 10;
        ActorId actorId = new ActorId(id);
        assertEquals(actorId, actorId);
    }

    @Test
    void equalsShouldReturnTrueForEqualValues() {
        int id = 10;
        ActorId actorId1 = new ActorId(id);
        ActorId actorId2 = new ActorId(id);
        assertEquals(actorId1, actorId2);
    }

    @Test
    void equalsShouldReturnFalseForDifferentValues() {
        int id1 = 10;
        int id2 = 20;
        ActorId actorId1 = ActorId.of(id1);
        ActorId actorId2 = ActorId.of(id2);
        assertNotEquals(actorId1, actorId2);
    }

    @Test
    void equalsShouldReturnFalseForDifferentObjectType() {
        int id = 10;
        ActorId actorId = ActorId.of(id);
        assertNotEquals("Some String", actorId);
    }

    @Test
    void hashCodeShouldBeEqualForEqualValues() {
        int id1 = 10;
        int id2 = 10;
        ActorId actorId1 = ActorId.of(id1);
        ActorId actorId2 = ActorId.of(id2);
        assertEquals(actorId1.hashCode(), actorId2.hashCode());
    }

    @Test
    void hashCodeShouldBeDifferentForDifferentValues() {
        int id1 = 10;
        int id2 = 20;
        ActorId actorId1 = ActorId.of(id1);
        ActorId actorId2 = ActorId.of(id2);
        assertNotEquals(actorId1.hashCode(), actorId2.hashCode());
    }
}