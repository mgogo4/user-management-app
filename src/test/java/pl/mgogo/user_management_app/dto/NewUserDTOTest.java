/*
 * Copyright (c) 2019 the original author or authors.
 */

package pl.mgogo.user_management_app.dto;

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
 * {@link NewUserDTO} unit test class.
 *
 * @author Michał Gogolewski
 */
class NewUserDTOTest {
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
        NewUserDTO newUserDTO = new NewUserDTO();
        newUserDTO.setName(null);
        newUserDTO.setSurname(DEFAULT_STRING);
        newUserDTO.setSalary(DEFAULT_INTEGER);
        newUserDTO.setGrade(DEFAULT_INTEGER);

        // When
        Set<ConstraintViolation<NewUserDTO>> violations = validator.validate(newUserDTO);

        // Then
        Assertions.assertFalse(violations.isEmpty());
        Assertions.assertEquals(ONE_CONSTRAINT_VIOLATION_EXPECTED, violations.size());
    }

    @Test
    void emptyName_oneConstraintViolated() {
        // Given
        NewUserDTO newUserDTO = new NewUserDTO();
        newUserDTO.setName(EMPTY_STRING);
        newUserDTO.setSurname(DEFAULT_STRING);
        newUserDTO.setSalary(DEFAULT_INTEGER);
        newUserDTO.setGrade(DEFAULT_INTEGER);

        // When
        Set<ConstraintViolation<NewUserDTO>> violations = validator.validate(newUserDTO);

        // Then
        Assertions.assertFalse(violations.isEmpty());
        Assertions.assertEquals(ONE_CONSTRAINT_VIOLATION_EXPECTED, violations.size());
    }

    @Test
    void whitespacesAsName_oneConstraintViolated() {
        // Given
        NewUserDTO newUserDTO = new NewUserDTO();
        newUserDTO.setName(WHITESPACES_STRING);
        newUserDTO.setSurname(DEFAULT_STRING);
        newUserDTO.setSalary(DEFAULT_INTEGER);
        newUserDTO.setGrade(DEFAULT_INTEGER);

        // When
        Set<ConstraintViolation<NewUserDTO>> violations = validator.validate(newUserDTO);

        // Then
        Assertions.assertFalse(violations.isEmpty());
        Assertions.assertEquals(ONE_CONSTRAINT_VIOLATION_EXPECTED, violations.size());
    }

    @Test
    void nameLongerThan64Characters_oneConstraintViolated() {
        // Given
        NewUserDTO newUserDTO = new NewUserDTO();
        newUserDTO.setName(LENGTH_65_CHARACTERS_STRINGS);
        newUserDTO.setSurname(DEFAULT_STRING);
        newUserDTO.setSalary(DEFAULT_INTEGER);
        newUserDTO.setGrade(DEFAULT_INTEGER);

        // When
        Set<ConstraintViolation<NewUserDTO>> violations = validator.validate(newUserDTO);

        // Then
        Assertions.assertFalse(violations.isEmpty());
        Assertions.assertEquals(ONE_CONSTRAINT_VIOLATION_EXPECTED, violations.size());
    }

    @Test
    void nullSurname_oneConstraintViolated() {
        // Given
        NewUserDTO newUserDTO = new NewUserDTO();
        newUserDTO.setName(DEFAULT_STRING);
        newUserDTO.setSurname(null);
        newUserDTO.setSalary(DEFAULT_INTEGER);
        newUserDTO.setGrade(DEFAULT_INTEGER);

        // When
        Set<ConstraintViolation<NewUserDTO>> violations = validator.validate(newUserDTO);

        // Then
        Assertions.assertFalse(violations.isEmpty());
        Assertions.assertEquals(ONE_CONSTRAINT_VIOLATION_EXPECTED, violations.size());
    }

    @Test
    void emptySurname_oneConstraintViolated() {
        // Given
        NewUserDTO newUserDTO = new NewUserDTO();
        newUserDTO.setName(DEFAULT_STRING);
        newUserDTO.setSurname(EMPTY_STRING);
        newUserDTO.setSalary(DEFAULT_INTEGER);
        newUserDTO.setGrade(DEFAULT_INTEGER);

        // When
        Set<ConstraintViolation<NewUserDTO>> violations = validator.validate(newUserDTO);

        // Then
        Assertions.assertFalse(violations.isEmpty());
        Assertions.assertEquals(ONE_CONSTRAINT_VIOLATION_EXPECTED, violations.size());
    }

    @Test
    void whitespacesAsSurname_oneConstraintViolated() {
        // Given
        NewUserDTO newUserDTO = new NewUserDTO();
        newUserDTO.setName(DEFAULT_STRING);
        newUserDTO.setSurname(WHITESPACES_STRING);
        newUserDTO.setSalary(DEFAULT_INTEGER);
        newUserDTO.setGrade(DEFAULT_INTEGER);

        // When
        Set<ConstraintViolation<NewUserDTO>> violations = validator.validate(newUserDTO);

        // Then
        Assertions.assertFalse(violations.isEmpty());
        Assertions.assertEquals(ONE_CONSTRAINT_VIOLATION_EXPECTED, violations.size());
    }

    @Test
    void surnameLongerThan64Characters_oneConstraintViolated() {
        // Given
        NewUserDTO newUserDTO = new NewUserDTO();
        newUserDTO.setName(DEFAULT_STRING);
        newUserDTO.setSurname(LENGTH_65_CHARACTERS_STRINGS);
        newUserDTO.setSalary(DEFAULT_INTEGER);
        newUserDTO.setGrade(DEFAULT_INTEGER);

        // When
        Set<ConstraintViolation<NewUserDTO>> violations = validator.validate(newUserDTO);

        // Then
        Assertions.assertFalse(violations.isEmpty());
        Assertions.assertEquals(ONE_CONSTRAINT_VIOLATION_EXPECTED, violations.size());
    }

    @Test
    void nullSalary_oneConstraintViolated() {
        // Given
        NewUserDTO newUserDTO = new NewUserDTO();
        newUserDTO.setName(DEFAULT_STRING);
        newUserDTO.setSurname(DEFAULT_STRING);
        newUserDTO.setSalary(null);
        newUserDTO.setGrade(DEFAULT_INTEGER);

        // When
        Set<ConstraintViolation<NewUserDTO>> violations = validator.validate(newUserDTO);

        // Then
        Assertions.assertFalse(violations.isEmpty());
        Assertions.assertEquals(ONE_CONSTRAINT_VIOLATION_EXPECTED, violations.size());
    }

    @Test
    void nullGrade_oneConstraintViolated() {
        // Given
        NewUserDTO newUserDTO = new NewUserDTO();
        newUserDTO.setName(DEFAULT_STRING);
        newUserDTO.setSurname(DEFAULT_STRING);
        newUserDTO.setSalary(DEFAULT_INTEGER);
        newUserDTO.setGrade(null);

        // When
        Set<ConstraintViolation<NewUserDTO>> violations = validator.validate(newUserDTO);

        // Then
        Assertions.assertFalse(violations.isEmpty());
        Assertions.assertEquals(ONE_CONSTRAINT_VIOLATION_EXPECTED, violations.size());
    }

    @Test
    void salaryValueZero_noConstraintsViolated() {
        // Given
        NewUserDTO newUserDTO = new NewUserDTO();
        newUserDTO.setName(DEFAULT_STRING);
        newUserDTO.setSurname(DEFAULT_STRING);
        newUserDTO.setSalary(ZERO_INTEGER);
        newUserDTO.setGrade(DEFAULT_INTEGER);

        // When
        Set<ConstraintViolation<NewUserDTO>> violations = validator.validate(newUserDTO);

        // Then
        Assertions.assertTrue(violations.isEmpty());
    }

    @Test
    void gradeValueZero_noConstraintsViolated() {
        // Given
        NewUserDTO newUserDTO = new NewUserDTO();
        newUserDTO.setName(DEFAULT_STRING);
        newUserDTO.setSurname(DEFAULT_STRING);
        newUserDTO.setSalary(DEFAULT_INTEGER);
        newUserDTO.setGrade(ZERO_INTEGER);

        // When
        Set<ConstraintViolation<NewUserDTO>> violations = validator.validate(newUserDTO);

        // Then
        Assertions.assertTrue(violations.isEmpty());
    }
}