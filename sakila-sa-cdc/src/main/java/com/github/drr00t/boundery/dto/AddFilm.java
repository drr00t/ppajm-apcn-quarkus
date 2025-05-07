package com.github.drr00t.boundery.dto;

public record AddFilm(String title, int releaseYear, String description,
                      int languageId, int originalLanguageId,
                      int rentalDuration, double rentalRate,
                      int length, double replacementCost, String rating,
                      String specialFeatures) {

}
