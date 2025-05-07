package io.github.drr00t.sakila.entity.domainModel;

import java.util.Collection;

public interface Aggregate<TIdentity> {
    TIdentity getIdentity();

    void raise(DomainEvent<?, ?> event);

    Collection<DomainEvent<?, ?>> getEvents();
}
