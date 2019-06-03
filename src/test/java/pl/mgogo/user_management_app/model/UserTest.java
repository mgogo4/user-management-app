/*
 * Copyright (c) 2019 the original author or authors.
 */

package pl.mgogo.user_management_app.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import pl.mgogo.user_management_app.util.TestHelperUtils;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Set;

/**
 * {@link User} unit test class.
 *
 * @author Michał Gogolewski
 */
class UserTest {
    private static final String DEFAULT_STRING = "STRING";
    private static final String EMPTY_STRING = "";
    private static final String WHITESPACES_STRING = "   ";
    private static final String LENGTH_65_CHARACTERS_STRINGS = TestHelperUtils.buildStringWithSize(65);
    private static final Integer DEFAULT_INTEGER = 1;
    private static final Integer ZERO_INTEGER = 0;
    private static final int ONE_CONSTRAINT_VIOLATION_EXPECTED = 1;

    private static Validator validator;

    @BeforeAll
    static void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    void nullName_oneConstraintViolated() {
        // Given
        User user = new User(null, DEFAULT_STRING, DEFAULT_INTEGER, DEFAULT_INTEGER);

        // When
        Set<ConstraintViolation<User>> violations = validator.validate(user);

        // Then
        Assertions.assertFalse(violations.isEmpty());
        Assertions.assertEquals(ONE_CONSTRAINT_VIOLATION_EXPECTED, violations.size());
    }

    @Test
    void emptyName_oneConstraintViolated() {
        // Given
        User user = new User(EMPTY_STRING, DEFAULT_STRING, DEFAULT_INTEGER, DEFAULT_INTEGER);

        // When
        Set<ConstraintViolation<User>> violations = validator.validate(user);

        // Then
        Assertions.assertFalse(violations.isEmpty());
        Assertions.assertEquals(ONE_CONSTRAINT_VIOLATION_EXPECTED, violations.size());
    }

    @Test
    void whitespacesAsName_oneConstraintViolated() {
        // Given
        User user = new User(WHITESPACES_STRING, DEFAULT_STRING, DEFAULT_INTEGER, DEFAULT_INTEGER);

        // When
        Set<ConstraintViolation<User>> violations = validator.validate(user);

        // Then
        Assertions.assertFalse(violations.isEmpty());
        Assertions.assertEquals(ONE_CONSTRAINT_VIOLATION_EXPECTED, violations.size());
    }

    @Test
    void nameLongerThan64Characters_oneConstraintViolated() {
        // Given
        User user = new User(LENGTH_65_CHARACTERS_STRINGS, DEFAULT_STRING, DEFAULT_INTEGER, DEFAULT_INTEGER);

        // When
        Set<ConstraintViolation<User>> violations = validator.validate(user);

        // Then
        Assertions.assertFalse(violations.isEmpty());
        Assertions.assertEquals(ONE_CONSTRAINT_VIOLATION_EXPECTED, violations.size());
    }

    @Test
    void nullSurname_oneConstraintViolated() {
        // Given
        User user = new User(DEFAULT_STRING, null, DEFAULT_INTEGER, DEFAULT_INTEGER);

        // When
        Set<ConstraintViolation<User>> violations = validator.validate(user);

        // Then
        Assertions.assertFalse(violations.isEmpty());
        Assertions.assertEquals(ONE_CONSTRAINT_VIOLATION_EXPECTED, violations.size());
    }

    @Test
    void emptySurname_oneConstraintViolated() {
        // Given
        User user = new User(DEFAULT_STRING, EMPTY_STRING, DEFAULT_INTEGER, DEFAULT_INTEGER);

        // When
        Set<ConstraintViolation<User>> violations = validator.validate(user);

        // Then
        Assertions.assertFalse(violations.isEmpty());
        Assertions.assertEquals(ONE_CONSTRAINT_VIOLATION_EXPECTED, violations.size());
    }

    @Test
    void whitespacesAsSurname_oneConstraintViolated() {
        // Given
        User user = new User(DEFAULT_STRING, WHITESPACES_STRING, DEFAULT_INTEGER, DEFAULT_INTEGER);

        // When
        Set<ConstraintViolation<User>> violations = validator.validate(user);

        // Then
        Assertions.assertFalse(violations.isEmpty());
        Assertions.assertEquals(ONE_CONSTRAINT_VIOLATION_EXPECTED, violations.size());
    }

    @Test
    void surnameLongerThan64Characters_oneConstraintViolated() {
        // Given
        User user = new User(DEFAULT_STRING, LENGTH_65_CHARACTERS_STRINGS, DEFAULT_INTEGER, DEFAULT_INTEGER);

        // When
        Set<ConstraintViolation<User>> violations = validator.validate(user);

        // Then
        Assertions.assertFalse(violations.isEmpty());
        Assertions.assertEquals(ONE_CONSTRAINT_VIOLATION_EXPECTED, violations.size());
    }

    @Test
    void nullSalary_oneConstraintViolated() {
        // Given
        User user = new User(DEFAULT_STRING, DEFAULT_STRING, DEFAULT_INTEGER, null);

        // When
        Set<ConstraintViolation<User>> violations = validator.validate(user);

        // Then
        Assertions.assertFalse(violations.isEmpty());
        Assertions.assertEquals(ONE_CONSTRAINT_VIOLATION_EXPECTED, violations.size());
    }

    @Test
    void nullGrade_oneConstraintViolated() {
        // Given
        User user = new User(DEFAULT_STRING, DEFAULT_STRING, null, DEFAULT_INTEGER);

        // When
        Set<ConstraintViolation<User>> violations = validator.validate(user);

        // Then
        Assertions.assertFalse(violations.isEmpty());
        Assertions.assertEquals(ONE_CONSTRAINT_VIOLATION_EXPECTED, violations.size());
    }

    @Test
    void salaryValueZero_noConstraintsViolated() {
        // Given
        User user = new User(DEFAULT_STRING, DEFAULT_STRING, DEFAULT_INTEGER, ZERO_INTEGER);

        // When
        Set<ConstraintViolation<User>> violations = validator.validate(user);

        // Then
        Assertions.assertTrue(violations.isEmpty());
    }

    @Test
    void gradeValueZero_noConstraintsViolated() {
        // Given
        User user = new User(DEFAULT_STRING, DEFAULT_STRING, ZERO_INTEGER, DEFAULT_INTEGER);

        // When
        Set<ConstraintViolation<User>> violations = validator.validate(user);

        // Then
        Assertions.assertTrue(violations.isEmpty());
    }
}