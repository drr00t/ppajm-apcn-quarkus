package io.github.drr00t.sakila.control.validation;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import io.quarkus.test.junit.QuarkusTest;

@QuarkusTest
public class ValidationResultTest {

    @Test
    public void testIsOk() {
        ValidationResult result = ValidationResult.isOk();
        assertTrue(result.isValid());
    }

    @Test
    public void testNotOk() {
        String errorMessage = "Error occurred";
        ValidationResult result = ValidationResult.notOk(List.of(errorMessage));
        assertFalse(result.isValid());
        assertEquals(errorMessage, result.errorMessages().get(0));
    }

    @Test
    public void testIsNotOk() {
        String errorMessage = "Error occurred";
        ValidationResult result = ValidationResult.notOk(List.of(errorMessage));
        assertTrue(result.isNotOk());
    }

    @Test
    public void testIsValid() {
        ValidationResult result = ValidationResult.isOk();
        assertTrue(result.isValid());
    }

    @Test
    public void testMessage() {
        String errorMessage = "Error occurred";
        ValidationResult result = ValidationResult.notOk(List.of(errorMessage));
        assertEquals(errorMessage, result.errorMessages().get(0));
    }
}