package io.github.drr00t.sakila.entity.domainModel;

import java.time.Instant;

public interface DomainEvent<TAggregateId, TPayload> {

    TAggregateId getAggregateId();

    TPayload getPayload();

    String getType();

    String getEventType();

    Instant getEventTime();

}
