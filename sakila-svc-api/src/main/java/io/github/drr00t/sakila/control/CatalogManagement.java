package io.github.drr00t.sakila.control;

import io.github.drr00t.sakila.boundary.AddActor;
import io.github.drr00t.sakila.boundary.CatalogRepository;
import io.github.drr00t.sakila.boundary.Result;
import io.github.drr00t.sakila.control.validation.ValidationRule;
import io.github.drr00t.sakila.entity.Actor;
import io.github.drr00t.sakila.entity.ActorId;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.util.List;

@ApplicationScoped
public class CatalogManagement {
//    private final HashMap<String, AddActor> actors;

    @Inject
    CatalogRepository repository;

    public Result<ActorId> createActor(AddActor actor) {
        ValidationRule<Actor> actorVal = new ValidationRule<>();
        var result = actorVal.validate(Actor.newActor(actor.firstName(), actor.lastName()));

        if (!result.isValid()) {
            return Result.notOk(result.errorMessages());
        }

        var saved = repository.save(result.entity());

        if (!saved.isValid()) {
            return saved;
        }
//        String key = actor.firstName().toUpperCase() + "_" + actor.lastName().toUpperCase();
//
//        if (actors.containsKey(key)) {
//            return Result.notOk(List.of("Actor already exists"));
//        }
//
//        actors.put(key, actor);

        return saved;
    }

    public Result<List<Actor>> getAll() {
        return repository.findAll();
    }

//    public Uni<ValidationResult> remove(String firstName, String lastName) {
//        String key = firstName.toUpperCase() + "_" + lastName.toUpperCase();
//        if (!actors.containsKey(key)) {
//            return Uni.createFrom().item(() -> ValidationResult.notOk(List.of("Actor not found")));
//        }
//        actors.remove(key);
//        return Uni.createFrom().item(ValidationResult::isOk);
//    }

//    public Uni<ValidationResult> get(String key) {
//        if (!actors.containsKey(key)) {
//            return Uni.createFrom().item(() -> ValidationResult.notOk(List.of("Actor not found")));
//        }
//        return Uni.createFrom().item(actors.get(key));
//    }
//
//    public Uni<ValidationResult> update(String firstName, String lastName, UpdateActor actor) {
//        String key = firstName.toUpperCase() + "_" + lastName.toUpperCase();
//        if (!actors.containsKey(key)) {
//            return Uni.createFrom().item(() -> ValidationResult.notOk(List.of("Actor not found")));
//        }
//        actors.put(key, actor);
//        return Uni.createFrom().item(ValidationResult::isOk);
//    }
}
