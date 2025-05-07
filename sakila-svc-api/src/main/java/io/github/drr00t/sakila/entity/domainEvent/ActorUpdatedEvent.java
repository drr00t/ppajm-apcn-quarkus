package io.github.drr00t.sakila.entity.domainEvent;

import io.github.drr00t.sakila.entity.Actor;
import io.github.drr00t.sakila.entity.ActorId;
import io.github.drr00t.sakila.entity.domainModel.BaseDomainEvent;

import java.time.Instant;
import java.util.SortedMap;
import java.util.TreeMap;

public class ActorUpdatedEvent extends BaseDomainEvent<ActorId, Actor, SortedMap<String, Object>> {
    private static final String TYPE = "Actor";
    private static final String EVENT_TYPE = "ActorUpdated";

    public ActorUpdatedEvent(Actor updActor, Instant eventTime) {
        super(TYPE, EVENT_TYPE, eventTime, updActor.getIdentity(), updActor);
    }

    public static ActorUpdatedEvent of(Actor updActor, Instant lastUpdate) {
        return new ActorUpdatedEvent(updActor, lastUpdate);
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
