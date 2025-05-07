package io.github.drr00t.sakila.entity.domainModel;


import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.Set;

public class PrimaryBaseEntity<TIdentity> extends BaseEntity<TIdentity> implements Aggregate<TIdentity> {

    private final Set<DomainEvent<?, ?>> domainEvents = new LinkedHashSet<>();


    public PrimaryBaseEntity(TIdentity identity) {
        super(identity);
    }

    @Override
    public void raise(DomainEvent<?, ?> event) {
        domainEvents.add(event);
    }

    @Override
    public Collection<DomainEvent<?, ?>> getEvents() {
        return domainEvents;
    }
}
