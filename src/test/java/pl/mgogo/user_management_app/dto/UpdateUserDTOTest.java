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
 * {@link UpdateUserDTO} unit test class.
 *
 * @author Michał Gogolewski
 */
class UpdateUserDTOTest {
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
    void nullName_noConstraintsViolated() {
        // Given
        UpdateUserDTO updateUserDTO = new UpdateUserDTO();
        updateUserDTO.setName(null);
        updateUserDTO.setSurname(DEFAULT_STRING);
        updateUserDTO.setSalary(DEFAULT_INTEGER);
        updateUserDTO.setGrade(DEFAULT_INTEGER);

        // When
        Set<ConstraintViolation<UpdateUserDTO>> violations = validator.validate(updateUserDTO);

        // Then
        Assertions.assertTrue(violations.isEmpty());
    }

    @Test
    void emptyName_noConstraintsViolated() {
        // Given
        UpdateUserDTO updateUserDTO = new UpdateUserDTO();
        updateUserDTO.setName(EMPTY_STRING);
        updateUserDTO.setSurname(DEFAULT_STRING);
        updateUserDTO.setSalary(DEFAULT_INTEGER);
        updateUserDTO.setGrade(DEFAULT_INTEGER);

        // When
        Set<ConstraintViolation<UpdateUserDTO>> violations = validator.validate(updateUserDTO);

        // Then
        Assertions.assertTrue(violations.isEmpty());
    }

    @Test
    void whitespacesAsName_noConstraintsViolated() {
        // Given
        UpdateUserDTO updateUserDTO = new UpdateUserDTO();
        updateUserDTO.setName(WHITESPACES_STRING);
        updateUserDTO.setSurname(DEFAULT_STRING);
        updateUserDTO.setSalary(DEFAULT_INTEGER);
        updateUserDTO.setGrade(DEFAULT_INTEGER);

        // When
        Set<ConstraintViolation<UpdateUserDTO>> violations = validator.validate(updateUserDTO);

        // Then
        Assertions.assertTrue(violations.isEmpty());
    }

    @Test
    void nameExceeds64Characters_oneConstraintViolated() {
        // Given
        UpdateUserDTO updateUserDTO = new UpdateUserDTO();
        updateUserDTO.setName(LENGTH_65_CHARACTERS_STRINGS);
        updateUserDTO.setSurname(DEFAULT_STRING);
        updateUserDTO.setSalary(DEFAULT_INTEGER);
        updateUserDTO.setGrade(DEFAULT_INTEGER);

        // When
        Set<ConstraintViolation<UpdateUserDTO>> violations = validator.validate(updateUserDTO);

        // Then
        Assertions.assertFalse(violations.isEmpty());
        Assertions.assertEquals(ONE_CONSTRAINT_VIOLATION_EXPECTED, violations.size());
    }

    @Test
    void nullSurname_noConstraintsViolated() {
        // Given
        UpdateUserDTO updateUserDTO = new UpdateUserDTO();
        updateUserDTO.setName(DEFAULT_STRING);
        updateUserDTO.setSurname(null);
        updateUserDTO.setSalary(DEFAULT_INTEGER);
        updateUserDTO.setGrade(DEFAULT_INTEGER);

        // When
        Set<ConstraintViolation<UpdateUserDTO>> violations = validator.validate(updateUserDTO);

        // Then
        Assertions.assertTrue(violations.isEmpty());
    }

    @Test
    void emptySurname_noConstraintsViolated() {
        // Given
        UpdateUserDTO updateUserDTO = new UpdateUserDTO();
        updateUserDTO.setName(DEFAULT_STRING);
        updateUserDTO.setSurname(EMPTY_STRING);
        updateUserDTO.setSalary(DEFAULT_INTEGER);
        updateUserDTO.setGrade(DEFAULT_INTEGER);

        // When
        Set<ConstraintViolation<UpdateUserDTO>> violations = validator.validate(updateUserDTO);

        // Then
        Assertions.assertTrue(violations.isEmpty());
    }

    @Test
    void whitespacesAsSurname_noConstraintsViolated() {
        // Given
        UpdateUserDTO updateUserDTO = new UpdateUserDTO();
        updateUserDTO.setName(DEFAULT_STRING);
        updateUserDTO.setSurname(WHITESPACES_STRING);
        updateUserDTO.setSalary(DEFAULT_INTEGER);
        updateUserDTO.setGrade(DEFAULT_INTEGER);

        // When
        Set<ConstraintViolation<UpdateUserDTO>> violations = validator.validate(updateUserDTO);

        // Then
        Assertions.assertTrue(violations.isEmpty());
    }

    @Test
    void surnameExceeds64Characters_oneConstraintViolated() {
        // Given
        UpdateUserDTO updateUserDTO = new UpdateUserDTO();
        updateUserDTO.setName(DEFAULT_STRING);
        updateUserDTO.setSurname(LENGTH_65_CHARACTERS_STRINGS);
        updateUserDTO.setSalary(DEFAULT_INTEGER);
        updateUserDTO.setGrade(DEFAULT_INTEGER);

        // When
        Set<ConstraintViolation<UpdateUserDTO>> violations = validator.validate(updateUserDTO);

        // Then
        Assertions.assertFalse(violations.isEmpty());
        Assertions.assertEquals(ONE_CONSTRAINT_VIOLATION_EXPECTED, violations.size());
    }

    @Test
    void nullSalary_noConstraintsViolated() {
        // Given
        UpdateUserDTO updateUserDTO = new UpdateUserDTO();
        updateUserDTO.setName(DEFAULT_STRING);
        updateUserDTO.setSurname(DEFAULT_STRING);
        updateUserDTO.setSalary(null);
        updateUserDTO.setGrade(DEFAULT_INTEGER);

        // When
        Set<ConstraintViolation<UpdateUserDTO>> violations = validator.validate(updateUserDTO);

        // Then
        Assertions.assertTrue(violations.isEmpty());
    }

    @Test
    void nullGrade_noConstraintsViolated() {
        // Given
        UpdateUserDTO updateUserDTO = new UpdateUserDTO();
        updateUserDTO.setName(DEFAULT_STRING);
        updateUserDTO.setSurname(DEFAULT_STRING);
        updateUserDTO.setSalary(DEFAULT_INTEGER);
        updateUserDTO.setGrade(null);

        // When
        Set<ConstraintViolation<UpdateUserDTO>> violations = validator.validate(updateUserDTO);

        // Then
        Assertions.assertTrue(violations.isEmpty());
    }

    @Test
    void salaryValueZero_noConstraintsViolated() {
        // Given
        UpdateUserDTO updateUserDTO = new UpdateUserDTO();
        updateUserDTO.setName(DEFAULT_STRING);
        updateUserDTO.setSurname(DEFAULT_STRING);
        updateUserDTO.setSalary(ZERO_INTEGER);
        updateUserDTO.setGrade(DEFAULT_INTEGER);

        // When
        Set<ConstraintViolation<UpdateUserDTO>> violations = validator.validate(updateUserDTO);

        // Then
        Assertions.assertTrue(violations.isEmpty());
    }

    @Test
    void gradeValueZero_noConstraintsViolated() {
        // Given
        UpdateUserDTO updateUserDTO = new UpdateUserDTO();
        updateUserDTO.setName(DEFAULT_STRING);
        updateUserDTO.setSurname(DEFAULT_STRING);
        updateUserDTO.setSalary(DEFAULT_INTEGER);
        updateUserDTO.setGrade(ZERO_INTEGER);

        // When
        Set<ConstraintViolation<UpdateUserDTO>> violations = validator.validate(updateUserDTO);

        // Then
        Assertions.assertTrue(violations.isEmpty());
    }
}