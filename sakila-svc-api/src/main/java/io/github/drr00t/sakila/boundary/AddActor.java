package io.github.drr00t.sakila.boundary;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record AddActor(
        @NotNull(message = "Last update cannot be null")
        @Size(min = 1, max = 45, message = "Last update is too long")
        String firstName,
        @NotNull(message = "Last update cannot be null")
        @Size(min = 1, max = 45, message = "Last update is too long")
        String lastName
) {


}
