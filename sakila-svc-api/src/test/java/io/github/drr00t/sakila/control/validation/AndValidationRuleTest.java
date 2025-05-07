package io.github.drr00t.sakila.control.validation;

import io.github.drr00t.sakila.boundary.Result;
import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@QuarkusTest
public class AndValidationRuleTest {

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
                return Result.isOk(input);
            }
        };

        Object toValidate = new Object();

        ValidationRule<Object> orValidationRule = new AndValidationRule<>(firstRule, secondRule);
        Result<Object> result = orValidationRule.validate(toValidate);

        assertTrue(result.isValid());
        assertEquals(0, result.errorMessages().size());
    }

    @Test
    public void testSecondRuleValid() {
        ValidationRule<Object> firstRule = new ValidationRule<Object>() {
            @Override
            public Result<Object> validate(Object input) {
                return Result.notOk(List.of("Invalid"));
            }
        };
        ValidationRule<Object> secondRule = new ValidationRule<Object>() {
            @Override
            public Result<Object> validate(Object input) {
                return Result.isOk(input);
            }
        };
        Object toValidate = new Object();

        ValidationRule<Object> orValidationRule = new AndValidationRule<>(firstRule, secondRule);
        Result<Object> result = orValidationRule.validate(toValidate);

        assertFalse(result.isValid());
        assertEquals(1, result.errorMessages().size());
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

        ValidationRule<Object> andValidationRule = new AndValidationRule<>(firstRule, secondRule);
        Result<Object> result = andValidationRule.validate(toValidate);

        assertFalse(result.isValid());
        assertEquals(1, result.errorMessages().size());
    }

    @Test
    public void testCheckThreeRulesValid() {
        ValidationRule<Object> zeroRule = new ValidationRule<>() {
            @Override
            public Result<Object> validate(Object input) {
                return Result.isOk(input);
            }
        };
        ValidationRule<Object> firstRule = new ValidationRule<>() {
            @Override
            public Result<Object> validate(Object input) {
                return Result.isOk(input);
            }
        };
        ValidationRule<Object> secondRule = new ValidationRule<>() {
            @Override
            public Result<Object> validate(Object input) {
                return Result.isOk(input);
            }
        };
        Object toValidate = new Object();

        ValidationRule<Object> andValidationRule = zeroRule.and(firstRule).and(secondRule);
        Result<Object> result = andValidationRule.validate(toValidate);

        assertTrue(result.isValid());
    }

    @Test
    public void testCheckThirdRuleFialing() {
        ValidationRule<Object> zeroRule = new ValidationRule<>() {
            @Override
            public Result<Object> validate(Object input) {
                return Result.isOk(input);
            }
        };
        ValidationRule<Object> firstRule = new ValidationRule<>() {
            @Override
            public Result<Object> validate(Object input) {
                return Result.isOk(input);
            }
        };
        ValidationRule<Object> secondRule = new ValidationRule<>() {
            @Override
            public Result<Object> validate(Object input) {
                return Result.notOk(List.of("Valid 3"));
            }
        };
        Object toValidate = new Object();

        ValidationRule<Object> orValidationRule = zeroRule.and(firstRule).and(secondRule);
        Result<Object> result = orValidationRule.validate(toValidate);

        assertFalse(result.isValid());
        assertEquals(1, result.errorMessages().size());
    }
}