package io.github.drr00t.sakila.control.validation;

import io.github.drr00t.sakila.boundary.Result;
import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;

@QuarkusTest
public class NotValidationRuleTest {

    @Test
    public void testInvalidRuleValid() {
        ValidationRule<Object> firstRule = new ValidationRule<>() {
            @Override
            public Result<Object> validate(Object input) {
                return Result.isOk(input);
            }
        };

        Object toValidate = new Object();

        ValidationRule<Object> orValidationRule = new NotValidationRule<>(firstRule);
        Result<Object> result = orValidationRule.validate(toValidate);

        assertFalse(result.isValid());
        assertEquals(0, result.errorMessages().size());
    }

    @Test
    public void testValidRuleInvalid() {
        ValidationRule<Object> firstRule = new ValidationRule<>() {
            @Override
            public Result<Object> validate(Object input) {
                return Result.notOk(Collections.singletonList("Invalid"));
            }
        };

        Object toValidate = new Object();

        ValidationRule<Object> orValidationRule = new NotValidationRule<>(firstRule);
        Result<Object> result = orValidationRule.validate(toValidate);

        assertTrue(result.isValid());
        assertEquals(0, result.errorMessages().size());
    }
}