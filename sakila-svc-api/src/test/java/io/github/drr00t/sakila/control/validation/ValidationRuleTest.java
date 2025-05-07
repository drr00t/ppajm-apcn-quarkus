package io.github.drr00t.sakila.control.validation;

import io.github.drr00t.sakila.boundary.Result;
import io.github.drr00t.sakila.control.validation.fixtures.FilmFake;
import io.github.drr00t.sakila.control.validation.fixtures.RequiredFieldRule;
import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@QuarkusTest
public class ValidationRuleTest {

    @Test
    public void testAndValidationRuleAllMustBeValid() {
        ValidationRule<Object> alwaysValid = new AlwaysValidRule();
        ValidationRule<Object> alwaysInvalid = new AlwaysInvalidRule();

        ValidationRule<Object> andRule = alwaysValid.and(alwaysInvalid);
        Result<Object> result = andRule.validate(new Object());

        assertFalse(result.isValid());
        assertEquals("Invalid", result.errorMessages().get(0));
    }

    @Test
    public void testOrValidationRuleOneMustBeValid() {
        ValidationRule<Object> alwaysValid = new AlwaysValidRule();
        ValidationRule<Object> alwaysInvalid = new AlwaysInvalidRule();

        ValidationRule<Object> orRule = alwaysValid.or(alwaysInvalid);
        Result<Object> result = orRule.validate(new Object());

        assertTrue(result.isValid());
    }

    @Test
    public void testNotValidationRule() {
        ValidationRule<Object> alwaysValid = new AlwaysValidRule();

        ValidationRule<Object> notRule = alwaysValid.not();
        Result<Object> result = notRule.validate(new Object());

        assertFalse(result.isValid());
    }

    @Test
    public void testRequiredFieldRule() {
        ValidationRule<FilmFake> requiredFieldRule = new RequiredFieldRule();

        FilmFake validFilm = new FilmFake("Nome", "Descricao");
        Result<FilmFake> validResult = requiredFieldRule.validate(validFilm);
        assertTrue(validResult.isValid());

        FilmFake invalidFilmNoNome = new FilmFake("", "Descricao");
        Result<FilmFake> invalidResultNoNome = requiredFieldRule.validate(invalidFilmNoNome);
        assertFalse(invalidResultNoNome.isValid());
        assertEquals("Name is required", invalidResultNoNome.errorMessages().get(0));

        FilmFake invalidFilmNoDescricao = new FilmFake("Nome", "Description is too long");
        Result<FilmFake> invalidResultNoDescricao = requiredFieldRule.validate(invalidFilmNoDescricao);
        assertFalse(invalidResultNoDescricao.isValid());
        assertEquals("Description is too long", invalidResultNoDescricao.errorMessages().get(0));
    }

    private static class AlwaysValidRule extends ValidationRule<Object> {
        @Override
        public Result<Object> validate(Object toValidate) {
            return Result.isOk(toValidate);
        }
    }

    private static class AlwaysInvalidRule extends ValidationRule<Object> {
        @Override
        public Result<Object> validate(Object toValidate) {
            return Result.notOk(List.of("Invalid"));
        }
    }
}