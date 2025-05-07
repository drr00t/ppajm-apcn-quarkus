package io.github.drr00t.sakila.boundary.persistence;

import io.github.drr00t.sakila.entity.Actor;
import io.github.drr00t.sakila.entity.ActorId;
import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.persistence.*;

import java.time.Instant;

@Entity
@Table(name = "actor")
public class ActorRecord extends PanacheEntityBase {
    @Id
    @Column(name = "actor_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int id;

    @Column(name = "first_name")
    public String firstName;

    @Column(name = "last_name")
    public String lastName;

    @Column(name = "last_update")
    public Instant lastUpdate = Instant.now();

    public static ActorRecord of(Actor actor) {
        var record = new ActorRecord();
        record.firstName = actor.getFirstName();
        record.lastName = actor.getLastName();
        record.lastUpdate = actor.getLastUpdate();
        return record;
    }

    public Actor toActor() {
        return new Actor(ActorId.of(id), firstName, lastName, lastUpdate);
    }
}
