package io.github.drr00t.sakila.entity;

import io.github.drr00t.sakila.entity.domainModel.PrimaryBaseEntity;
import io.github.drr00t.sakila.entity.domainEvent.ActorAddedEvent;
import io.github.drr00t.sakila.entity.domainEvent.ActorUpdatedEvent;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.Instant;
import java.util.Objects;

public class Actor extends PrimaryBaseEntity<ActorId> {

    @Size(min = 1, max = 45, message = "First name must be between 1 and 45 characters")
    private String firstName;

    @Size(min = 1, max = 45, message = "First name must be between 1 and 45 characters")
    private String lastName;

    @NotNull(message = "Last update cannot be null")
    private Instant lastUpdate;

    public Actor(ActorId actorId, String firstName, String lastName, Instant lastUpdate) {
        super(actorId);
        this.firstName = firstName;
        this.lastName = lastName;
        this.lastUpdate = lastUpdate;
    }

    public static Actor newActor(String firstName, String lastName) {
        var actorId = ActorId.of(1);
        var lastUpdate = Instant.now();
        var newActor = new Actor(actorId, firstName, lastName, lastUpdate);
        newActor.raise(ActorAddedEvent.of(newActor, lastUpdate));

        return newActor;
    }

    public static Actor Update(Actor actor, String firstName, String lastName) {
        var lastUpdate = Instant.now();
        var updActor = new Actor(actor.getIdentity(), firstName, lastName, lastUpdate);
        updActor.raise(ActorUpdatedEvent.of(updActor, lastUpdate));
        return updActor;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
        this.lastUpdate = Instant.now();
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {

        this.lastName = lastName;
        this.lastUpdate = Instant.now();
    }

    public Instant getLastUpdate() {
        return lastUpdate;
    }

    @Override
    public String toString() {
        return "Actor{" +
                "actorId=" + getIdentity() +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", lastUpdate=" + lastUpdate +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (!(o instanceof Actor actor))
            return false;

        if (!getIdentity().equals(actor.getIdentity()))
            return false;

        if (!firstName.equals(actor.firstName))
            return false;
        if (!lastName.equals(actor.lastName))
            return false;

        return lastUpdate.equals(actor.lastUpdate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(getIdentity(), firstName, lastName, lastUpdate);
    }
}
