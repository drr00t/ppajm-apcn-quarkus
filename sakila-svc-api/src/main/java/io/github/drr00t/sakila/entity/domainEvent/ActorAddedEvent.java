package io.github.drr00t.sakila.entity.domainEvent;

import io.github.drr00t.sakila.entity.Actor;
import io.github.drr00t.sakila.entity.ActorId;
import io.github.drr00t.sakila.entity.domainModel.BaseDomainEvent;

import java.time.Instant;
import java.util.Collections;
import java.util.SortedMap;
import java.util.TreeMap;

public class ActorAddedEvent extends BaseDomainEvent<ActorId, Actor, SortedMap<String, Object>> {

    private static final String TYPE = "Actor";
    private static final String EVENT_TYPE = "ActorCreated";

    public ActorAddedEvent(Actor actor, Instant eventTime) {
        super(TYPE, EVENT_TYPE, eventTime, actor.getIdentity(), actor);
    }

    public static ActorAddedEvent of(Actor newActor, Instant eventTime) {
        return new ActorAddedEvent(newActor, eventTime);
    }

    @Override
    protected SortedMap<String, Object> createPayload(Actor entityData) {
        SortedMap<String, Object> obj = new TreeMap<>();
        obj.put("firstName", entityData.getFirstName());
        obj.put("lastName", entityData.getLastName());
        obj.put("lastUpdate", entityData.getLastUpdate());

        return obj;
    }
}
