package io.github.drr00t.sakila.entity.domainEvent;

import io.github.drr00t.sakila.entity.Actor;
import io.github.drr00t.sakila.entity.ActorId;
import org.junit.jupiter.api.Test;

import java.time.Instant;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class ActorEventsTest {

    @Test
    void testOfCreatesValidActorAddedEvent() {
        // Arrange
        ActorId actorId = ActorId.of(1); // Replace with an appropriate ActorId implementation
        Actor newActor = new Actor(actorId, "John", "Doe", Instant.now());
        Instant eventTime = Instant.now();

        // Act
        ActorAddedEvent event = ActorAddedEvent.of(newActor, eventTime);

        // Assert
        assertNotNull(event, "The created event should not be null.");
        assertEquals(actorId, event.getAggregateId(), "The actor ID should match the aggregate ID.");
        assertEquals("Actor", event.getType(), "The type should be 'Actor'.");
        assertEquals("ActorCreated", event.getEventType(), "The event type should be 'ActorCreated'.");
        assertEquals(eventTime, event.getEventTime(), "The event time should match the specified time.");
        assertEquals(3, event.getPayload().size(), "The payload should not be null.");
    }

    @Test
    void testActorUpdatedEvent() {
        // Arrange
        ActorId actorId = ActorId.of(1); // Replace with an appropriate ActorId implementation
        Actor actor = new Actor(actorId, "John", "Doe", Instant.now());
        Instant eventTime = Instant.now();

        // Act
        ActorUpdatedEvent event = ActorUpdatedEvent.of(actor, eventTime);

        // Assert
        assertNotNull(event, "The created event should not be null.");
        assertEquals(actorId, event.getAggregateId(), "The actor ID should match the aggregate ID.");
        assertEquals("Actor", event.getType(), "The type should be 'Actor'.");
        assertEquals("ActorUpdated", event.getEventType(), "The event type should be 'ActorUpdated'.");
        assertEquals(eventTime, event.getEventTime(), "The event time should match the specified time.");
        assertEquals(3, event.getPayload().size(), "The payload should not be null.");
    }
}