// package com.github.drr00t.boundery;

// import java.time.Instant;

// import com.github.drr00t.entity.Film;

// import io.debezium.outbox.quarkus.ExportedEvent;
// import static io.quarkus.jsonp.JsonProviderHolder.jsonProvider;
// import jakarta.json.JsonObject;

// public class FilmCreated implements ExportedEvent<String, JsonObject> {

// private static final String TYPE = "Film";
// private static final String EVENT_TYPE = "FilmCreated";

// private final int filmId;
// private final Instant createdAt;
// private final JsonObject payload;

// public FilmCreated(Instant createdAt, Film film) {
// this.filmId = film.filmId;
// this.createdAt = createdAt;
// this.payload = createJsonPayload(film);
// }

// public static FilmCreated of(Film film) {
// return new FilmCreated(Instant.now(), film);
// }

// @Override
// public String getAggregateId() {
// return String.valueOf(filmId);
// }

// @Override
// public String getAggregateType() {
// return TYPE;
// }

// @Override
// public JsonObject getPayload() {
// return payload;
// }

// @Override
// public Instant getTimestamp() {
// return createdAt;
// }

// @Override
// public String getType() {
// return EVENT_TYPE;
// }

// private static JsonObject createJsonPayload(Film film) {
// return jsonProvider().createObjectBuilder()
// .add("filmId", film.filmId)
// .add("title", film.title)
// .add("releaseYear", film.releaseYear)
// .add("lastUpdate", film.lastUpdate.toString())
// .add("description", film.description)
// .add("languageId", film.languageId)
// .add("originalLanguageId", film.originalLanguageId)
// .add("rentalDuration", film.rentalDuration)
// .add("rentalRate", film.rentalRate)
// .add("length", film.length)
// .add("replacementCost", film.replacementCost)
// .add("rating", film.rating)
// .add("specialFeatures", film.specialFeatures)
// .build();
// }
// }
