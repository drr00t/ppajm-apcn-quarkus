package io.github.drr00t.sakila.entity.domainModel;

public class BaseEntity<TIdentity> {
    private final TIdentity identity;


    public BaseEntity(TIdentity identity) {
        this.identity = identity;
    }

    public TIdentity getIdentity() {
        return identity;
    }

}
