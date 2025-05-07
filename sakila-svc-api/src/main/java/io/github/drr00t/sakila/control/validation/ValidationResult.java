package io.github.drr00t.sakila.control.validation;

import java.util.Collections;
import java.util.List;


public record ValidationResult(boolean isValid, List<String> errorMessages) {
    public static ValidationResult isOk() {
        return new ValidationResult(true, Collections.emptyList());
    }

    public static ValidationResult notOk(List<String> errorMessages) {
        return new ValidationResult(false, errorMessages);
    }

    public boolean isNotOk() {
        return !isValid;
    }

}

