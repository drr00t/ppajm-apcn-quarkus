package io.github.drr00t.sakila.control.validation;

import io.github.drr00t.sakila.boundary.Result;

public class AndValidationRule<TObject> extends ValidationRule<TObject> {
    private final ValidationRule<TObject> first;
    private final ValidationRule<TObject> second;

    public AndValidationRule(ValidationRule<TObject> first, ValidationRule<TObject> second) {
        this.first = first;
        this.second = second;
    }

    @Override
    public Result<TObject> validate(TObject toValidate) {
        Result<TObject> firstResult = first.validate(toValidate);
        if (firstResult.isValid()) {
            return second.validate(toValidate);
        }

        return firstResult;
    }
}
