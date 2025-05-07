package io.github.drr00t.sakila.entity;

import java.util.Objects;

public class ActorId {
    int value;

    public ActorId(int value) {
        this.value = value;
    }

    public static ActorId of(int value) {
        return new ActorId(value);
    }

    public long getValue() {
        return value;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this)
            return true;
        if (!(obj instanceof ActorId actorId))
            return false;
        return Objects.equals(value, actorId.value);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(this.value);
    }
}