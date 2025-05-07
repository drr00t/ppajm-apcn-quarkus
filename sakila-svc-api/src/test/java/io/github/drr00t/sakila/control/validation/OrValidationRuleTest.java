package io.github.drr00t.sakila.control.validation;

import io.github.drr00t.sakila.boundary.Result;
import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@QuarkusTest
public class OrValidationRuleTest {

    @Test
    public void testFirstRuleValid() {
        ValidationRule<Object> firstRule = new ValidationRule<>() {
            @Override
            public Result<Object> validate(Object input) {
                return Result.isOk(input);
            }
        };
        ValidationRule<Object> secondRule = new ValidationRule<>() {
            @Override
            public Result<Object> validate(Object input) {
                return Result.notOk(List.of("Invalid"));
            }
        };

        Object toValidate = new Object();

        OrValidationRule<Object> orValidationRule = new OrValidationRule<>(firstRule, secondRule);
        Result<Object> result = orValidationRule.validate(toValidate);

        assertTrue(result.isValid());
        assertEquals(0, result.errorMessages().size());
    }

    @Test
    public void testSecondRuleValid() {
        ValidationRule<Object> firstRule = new ValidationRule<>() {
            @Override
            public Result<Object> validate(Object input) {
                return Result.notOk(List.of("Invalid"));
            }
        };
        ValidationRule<Object> secondRule = new ValidationRule<>() {
            @Override
            public Result<Object> validate(Object input) {
                return Result.isOk(input);
            }
        };
        Object toValidate = new Object();

        OrValidationRule<Object> orValidationRule = new OrValidationRule<>(firstRule, secondRule);
        Result<Object> result = orValidationRule.validate(toValidate);

        assertTrue(result.isValid());
        assertEquals(0, result.errorMessages().size());
    }

    @Test
    public void testBothRulesInvalid() {
        ValidationRule<Object> firstRule = new ValidationRule<>() {
            @Override
            public Result<Object> validate(Object input) {
                return Result.notOk(List.of("Invalid"));
            }
        };
        ValidationRule<Object> secondRule = new ValidationRule<>() {
            @Override
            public Result<Object> validate(Object input) {
                return Result.notOk(List.of("Invalid"));
            }
        };
        Object toValidate = new Object();

        OrValidationRule<Object> orValidationRule = new OrValidationRule<>(firstRule, secondRule);
        Result<Object> result = orValidationRule.validate(toValidate);

        assertFalse(result.isValid());
        assertEquals(2, result.errorMessages().size());
    }
}