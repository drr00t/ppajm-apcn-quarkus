package io.github.drr00t.sakila.boundary;

import java.util.Collections;
import java.util.List;

public record Result<T>(boolean isValid, T entity, List<String> errorMessages) {
    public static <T> Result<T> isOk(T entity) {
        return new Result<>(true, entity, Collections.emptyList());
    }

    public static <T> Result<T> notOk(List<String> errorMessages) {
        return new Result<>(false, null, errorMessages);
    }

}
