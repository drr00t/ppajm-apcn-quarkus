package io.github.drr00t.sakila.control.validation.fixtures;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class FilmFake {

    @NotBlank(message = "Name is required")
    private final String name;

    @Size(max = 10, message = "Description is too long")
    private final String description;

    public FilmFake(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }
}