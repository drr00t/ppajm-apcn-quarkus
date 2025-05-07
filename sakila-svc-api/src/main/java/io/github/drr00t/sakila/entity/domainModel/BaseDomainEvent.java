package io.github.drr00t.sakila.entity.domainModel;

import java.time.Instant;

public abstract class BaseDomainEvent<TAggregateId, TEntity, TPayload> implements DomainEvent<TAggregateId, TPayload> {
    private final String type;
    private final String eventType;

    private final Instant eventTime;

    private final TAggregateId aggregateId;
    private final TPayload payload;

    public BaseDomainEvent(String type, String eventType, Instant eventTime, TAggregateId aggregateId, TEntity payload) {
        this.aggregateId = aggregateId;
        this.type = type;
        this.eventType = eventType;
        this.eventTime = eventTime;
        this.payload = createPayload(payload);
    }

    public TAggregateId getAggregateId() {
        return aggregateId;
    }

    public TPayload getPayload() {
        return payload;
    }

    public String getType() {
        return type;
    }

    public String getEventType() {
        return eventType;
    }

    public Instant getEventTime() {
        return eventTime;
    }

    protected abstract TPayload createPayload(TEntity payload);
}
