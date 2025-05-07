package io.github.drr00t.sakila.boundary;

import io.github.drr00t.sakila.boundary.capabilities.Repository;
import io.github.drr00t.sakila.boundary.persistence.ActorRecord;
import io.github.drr00t.sakila.entity.Actor;
import io.github.drr00t.sakila.entity.ActorId;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;

import java.util.List;

@ApplicationScoped
@Transactional
public class CatalogRepository implements Repository<Actor, ActorId> {

    @Override
    public Result<Actor> findById(ActorId actorId) {
        var entity = (ActorRecord) ActorRecord.find("id", actorId.getValue()).firstResult();
        if (entity == null) {
            return Result.notOk(List.of("Actor not found"));
        }
        return Result.isOk(entity.toActor());
    }

    @Override
    public Result<ActorId> save(Actor actor) {
        var entity = new ActorRecord();
        entity.firstName = actor.getFirstName();
        entity.lastName = actor.getLastName();
        entity.lastUpdate = actor.getLastUpdate();
        entity.persist();

        if (!entity.isPersistent()) {
            return Result.notOk(List.of("Actor not saved"));
        }

        return Result.isOk(ActorId.of(entity.id));
    }

    @Override
    public Result<ActorId> delete(ActorId identity) {
        var entityToDelete = ActorRecord.findById(identity.getValue());
        if (entityToDelete == null) {
            return Result.notOk(List.of("Actor not found"));
        }

        entityToDelete.delete();

        if (entityToDelete.isPersistent()) {
            return Result.notOk(List.of("Actor not deleted"));
        }

        return Result.isOk(identity);
    }

    @Override
    public Result<ActorId> update(Actor actor) {
        var entity = (ActorRecord) ActorRecord.findById(actor.getIdentity().getValue());

        if (entity == null) {
            return Result.notOk(List.of("Actor not found"));
        }

        entity.firstName = actor.getFirstName();
        entity.lastName = actor.getLastName();
        entity.lastUpdate = actor.getLastUpdate();
        entity.persist();

        if (!entity.isPersistent()) {
            return Result.notOk(List.of("Actor not updated"));
        }

        return Result.isOk(actor.getIdentity());
    }

    public Result<List<Actor>> findAll() {
        List<ActorRecord> entities = ActorRecord.listAll();
        if (entities == null || entities.isEmpty()) {
            return Result.notOk(List.of("No actors found"));
        }
        return Result.isOk(entities.stream().map(ActorRecord::toActor).toList());
    }
}
