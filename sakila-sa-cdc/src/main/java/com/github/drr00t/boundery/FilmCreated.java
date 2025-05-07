
/*
 * Copyright (C) Book Patterns and Practices for Modern Java Architecture adribeiro@gmail.com
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *         http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.github.drr00t.boundery;

import java.time.Instant;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.github.drr00t.entity.Film;

import io.debezium.outbox.quarkus.ExportedEvent;

public class FilmCreated implements ExportedEvent<String, JsonNode> {
    private static final String TYPE = "Film";
    private static final String EVENT_TYPE = "FilmCreated";
    private static ObjectMapper mapper = new ObjectMapper();
    private final int filmId;
    private final Instant createdAt;
    private final JsonNode payload;

    public FilmCreated(Instant createdAt, Film film) {
        this.filmId = film.filmId;
        this.createdAt = createdAt;
        this.payload = createJsonPayload(film);
    }

    public static FilmCreated of(Film film) {
        return new FilmCreated(Instant.now(), film);
    }

    private static JsonNode createJsonPayload(Film film) {
        ObjectNode asJson = mapper.createObjectNode();
        asJson.put("filmId", film.filmId);
        asJson.put("title", film.title);
        asJson.put("releaseYear", film.releaseYear);
        asJson.put("description", film.description);
        asJson.put("languageId", film.languageId);
        asJson.put("originalLanguageId", film.originalLanguageId);
        asJson.put("rentalDuration", film.rentalDuration);
        asJson.put("rentalRate", film.rentalRate);
        asJson.put("length", film.length);
        asJson.put("replacementCost", film.replacementCost);
        asJson.put("rating", film.rating);
        asJson.put("specialFeatures", film.specialFeatures);
        asJson.put("lastUpdate", film.lastUpdate.toString());
        return asJson;
        // return jsonProvider().createObjectBuilder()
        // .add("filmId", film.filmId)
        // .add("title", film.title)
        // // .add("releaseYear", film.releaseYear)
        // // .add("description", film.description)
        // .add("languageId", film.languageId)
        // // .add("originalLanguageId", film.originalLanguageId)
        // // .add("rentalDuration", film.rentalDuration)
        // // .add("rentalRate", film.rentalRate)
        // // .add("length", film.length)
        // .add("replacementCost", film.replacementCost)
        // // .add("rating", film.rating)
        // // .add("specialFeatures", film.specialFeatures)
        // .build();
    }

    @Override
    public String getAggregateId() {
        return String.valueOf(filmId);
    }

    @Override
    public String getAggregateType() {
        return TYPE;
    }

    @Override
    public JsonNode getPayload() {
        return payload;
    }

    @Override
    public Instant getTimestamp() {
        return createdAt;
    }

    @Override
    public String getType() {
        return EVENT_TYPE;
    }
}
