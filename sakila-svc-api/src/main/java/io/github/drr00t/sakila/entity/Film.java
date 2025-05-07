package io.github.drr00t.sakila.entity;

import io.github.drr00t.sakila.entity.domainModel.PrimaryBaseEntity;

private String title;
private String description;
private int releaseYear;
private int length;
private String rating;
public Film(FilmId filmId, String title, String description, int releaseYear, int length, String rating) {
    super(filmId);
    this.title = title;
    this.description = description;
    this.releaseYear = releaseYear;
    this.length = length;
    this.rating = rating;
}

public String getTitle() {
    return title;
}

public void setTitle(String title) {
    this.title = title;
}

public String getDescription() {
    return description;
}

public void setDescription(String description) {
    this.description = description;
}

public int getReleaseYear() {
    return releaseYear;
}

public void setReleaseYear(int releaseYear) {
    this.releaseYear = releaseYear;
}

public int getLength() {
    return length;
}

public void setLength(int length) {
    this.length = length;
}

public String getRating() {
    return rating;
}

public void setRating(String rating) {
    this.rating = rating;
}

public class Film extends PrimaryBaseEntity<FilmId>{
        }
