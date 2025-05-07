package io.github.drr00t.sakila.control.validation;

import io.github.drr00t.sakila.boundary.Result;

public class NotValidationRule<TObject> extends ValidationRule<TObject> {
    private final ValidationRule<TObject> rule;

    public NotValidationRule(ValidationRule<TObject> rule) {
        this.rule = rule;
    }

    @Override
    public Result<TObject> validate(TObject toValidate) {
        Result<TObject> result = rule.validate(toValidate);
        if (result.isValid()) {
            return Result.notOk(result.errorMessages());
        }
        return Result.isOk(toValidate);
    }
}
