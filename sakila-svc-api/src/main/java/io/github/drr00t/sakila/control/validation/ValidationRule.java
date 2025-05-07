package io.github.drr00t.sakila.control.validation;

import io.github.drr00t.sakila.boundary.Result;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class ValidationRule<TObject> {

    public Result<TObject> validate(TObject toValidate) {
        try (ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory()) {
            final Validator validator = validatorFactory.getValidator();
            final Set<ConstraintViolation<TObject>> constraintsViolations = validator.validate(toValidate);

            if (!constraintsViolations.isEmpty()) {
                List<String> errors = constraintsViolations.stream()
                        .map(ConstraintViolation::getMessage)
                        .collect(Collectors.toList());
                return Result.notOk(errors);
            }
        }
        return Result.isOk(toValidate);
    }

    public ValidationRule<TObject> and(ValidationRule<TObject> other) {
        return new AndValidationRule<>(this, other);
    }

    public ValidationRule<TObject> or(ValidationRule<TObject> other) {
        return new OrValidationRule<>(this, other);
    }

    public ValidationRule<TObject> not() {
        return new NotValidationRule<>(this);
    }

}