package com.github.drr00t.control;

import java.time.Instant;

import com.github.drr00t.boundery.FilmCreated;
import com.github.drr00t.boundery.dto.AddFilm;
import com.github.drr00t.entity.Film;

import io.debezium.outbox.quarkus.ExportedEvent;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.event.Event;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

@ApplicationScoped
public class FilmService {

    @Inject
    FilmRepository filmRepository;

    @Inject
    Event<ExportedEvent<?, ?>> event;

    @Transactional
    public void createFilm(AddFilm film) {
        var entity = Film.of(film.title(), film.releaseYear(), film.description(),
                film.languageId(), film.originalLanguageId(),
                film.rentalDuration(), film.rentalRate(), film.length(),
                film.replacementCost(), film.rating(), film.specialFeatures(), Instant.now());
        filmRepository.persist(entity);
        event.fire(FilmCreated.of(entity));
    }
}