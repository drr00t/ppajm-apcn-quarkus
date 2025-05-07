package io.github.drr00t.sakila.control.validation;

import io.github.drr00t.sakila.boundary.Result;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class OrValidationRule<TObject> extends ValidationRule<TObject> {
    private final ValidationRule<TObject> first;
    private final ValidationRule<TObject> second;

    public OrValidationRule(ValidationRule<TObject> first, ValidationRule<TObject> second) {
        this.first = first;
        this.second = second;
    }

    @Override
    public Result<TObject> validate(TObject toValidate) {
        Result<TObject> firstResult = first.validate(toValidate);

        if (firstResult.isValid()) {
            return Result.isOk(toValidate);
        }

        Result<TObject> secondResult = second.validate(toValidate);

        if (secondResult.isValid()) {
            return Result.isOk(toValidate);
        }

        List<String> errorList = Stream.concat(firstResult.errorMessages().stream(),
                        secondResult.errorMessages().stream())
                .collect(Collectors.toList());
        return Result.notOk(errorList);
    }
}
