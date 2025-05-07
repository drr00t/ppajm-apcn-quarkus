package com.github.drr00t.entity;

import java.time.Instant;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

// public enum mpaa_rating {G='G',PG='PG',PG_13='PG-13',R='R',NC_17='NC-17'};

@Entity
@Table(name = "film")
public class Film {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "film_id", nullable = false, unique = true)
    public int filmId;
    @Column(name = "title", nullable = false)
    public String title;
    @Column(name = "release_year")
    public int releaseYear;
    @Column(name = "last_update")
    public Instant lastUpdate;
    @Column(name = "description")
    public String description;
    @Column(name = "language_id", nullable = false)
    public int languageId;
    @Column(name = "original_language_id")
    public int originalLanguageId;
    @Column(name = "rental_duration", nullable = false)
    public int rentalDuration;
    @Column(name = "rental_rate", nullable = false)
    public double rentalRate;
    @Column(name = "length")
    public int length;
    @Column(name = "replacement_cost", nullable = false)
    public double replacementCost;
    @Column(name = "rating")
    public String rating;
    @Column(name = "special_features")
    public String specialFeatures;

    public Film() {
    }

    public Film(int filmId, String title, int releaseYear, Instant lastUpdate,
                String description, int languageId, int originalLanguageId,
                int rentalDuration, double rentalRate,
                Integer length, double replacementCost, String rating, String specialFeatures) {
        this.filmId = filmId;
        this.title = title;
        this.releaseYear = releaseYear;
        this.lastUpdate = lastUpdate;
        this.description = description;
        this.languageId = languageId;
        this.originalLanguageId = originalLanguageId;
        this.rentalDuration = rentalDuration;
        this.rentalRate = rentalRate;
        this.length = length;
        this.replacementCost = replacementCost;
        this.rating = rating;
        this.specialFeatures = specialFeatures;

    }

    public static Film of(String title, int releaseYear, String description,
                          int languageId, int originalLanguageId,
                          int rentalDuration, double rentalRate,
                          int length, double replacementCost, String rating,
                          String specialFeatures, Instant now) {
        return new Film(0, title, releaseYear, now,
                description, languageId, originalLanguageId, rentalDuration, rentalRate,
                length, replacementCost, rating, specialFeatures);
    }
}
