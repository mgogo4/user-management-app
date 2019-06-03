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
 * {@link UserDTO} unit test class.
 *
 * @author Michał Gogolewski
 */
class UserDTOTest {

    private static final Long NEGATIVE_LONG = -1L;
    private static final Long DEFAULT_LONG = 1L;
    private static final Long ZERO_LONG = 0L;
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
    void nullID_oneConstraintViolated() {
        // Given
        UserDTO userDTO = new UserDTO();
        userDTO.setId(null);
        userDTO.setName(DEFAULT_STRING);
        userDTO.setSurname(DEFAULT_STRING);
        userDTO.setSalary(DEFAULT_INTEGER);
        userDTO.setGrade(DEFAULT_INTEGER);

        // When
        Set<ConstraintViolation<UserDTO>> violations = validator.validate(userDTO);

        // Then
        Assertions.assertFalse(violations.isEmpty());
        Assertions.assertEquals(ONE_CONSTRAINT_VIOLATION_EXPECTED, violations.size());
    }

    @Test
    void negativeID_oneConstraintViolated() {
        // Given
        UserDTO userDTO = new UserDTO();
        userDTO.setId(NEGATIVE_LONG);
        userDTO.setName(DEFAULT_STRING);
        userDTO.setSurname(DEFAULT_STRING);
        userDTO.setSalary(DEFAULT_INTEGER);
        userDTO.setGrade(DEFAULT_INTEGER);

        // When
        Set<ConstraintViolation<UserDTO>> violations = validator.validate(userDTO);

        // Then
        Assertions.assertFalse(violations.isEmpty());
        Assertions.assertEquals(ONE_CONSTRAINT_VIOLATION_EXPECTED, violations.size());
    }

    @Test
    void idValueZero_oneConstraintViolated() {
        // Given
        UserDTO userDTO = new UserDTO();
        userDTO.setId(ZERO_LONG);
        userDTO.setName(DEFAULT_STRING);
        userDTO.setSurname(DEFAULT_STRING);
        userDTO.setSalary(DEFAULT_INTEGER);
        userDTO.setGrade(DEFAULT_INTEGER);

        // When
        Set<ConstraintViolation<UserDTO>> violations = validator.validate(userDTO);

        // Then
        Assertions.assertTrue(violations.isEmpty());
    }

    @Test
    void nullName_oneConstraintViolated() {
        // Given
        UserDTO userDTO = new UserDTO();
        userDTO.setId(DEFAULT_LONG);
        userDTO.setName(null);
        userDTO.setSurname(DEFAULT_STRING);
        userDTO.setSalary(DEFAULT_INTEGER);
        userDTO.setGrade(DEFAULT_INTEGER);

        // When
        Set<ConstraintViolation<UserDTO>> violations = validator.validate(userDTO);

        // Then
        Assertions.assertFalse(violations.isEmpty());
        Assertions.assertEquals(ONE_CONSTRAINT_VIOLATION_EXPECTED, violations.size());
    }

    @Test
    void emptyName_oneConstraintViolated() {
        // Given
        UserDTO userDTO = new UserDTO();
        userDTO.setId(DEFAULT_LONG);
        userDTO.setName(EMPTY_STRING);
        userDTO.setSurname(DEFAULT_STRING);
        userDTO.setSalary(DEFAULT_INTEGER);
        userDTO.setGrade(DEFAULT_INTEGER);

        // When
        Set<ConstraintViolation<UserDTO>> violations = validator.validate(userDTO);

        // Then
        Assertions.assertFalse(violations.isEmpty());
        Assertions.assertEquals(ONE_CONSTRAINT_VIOLATION_EXPECTED, violations.size());
    }

    @Test
    void whitespacesAsName_oneConstraintViolated() {
        // Given
        UserDTO userDTO = new UserDTO();
        userDTO.setId(DEFAULT_LONG);
        userDTO.setName(WHITESPACES_STRING);
        userDTO.setSurname(DEFAULT_STRING);
        userDTO.setSalary(DEFAULT_INTEGER);
        userDTO.setGrade(DEFAULT_INTEGER);

        // When
        Set<ConstraintViolation<UserDTO>> violations = validator.validate(userDTO);

        // Then
        Assertions.assertFalse(violations.isEmpty());
        Assertions.assertEquals(ONE_CONSTRAINT_VIOLATION_EXPECTED, violations.size());
    }

    @Test
    void nameLongerThan64Characters_oneConstraintViolated() {
        // Given
        UserDTO userDTO = new UserDTO();
        userDTO.setId(DEFAULT_LONG);
        userDTO.setName(LENGTH_65_CHARACTERS_STRINGS);
        userDTO.setSurname(DEFAULT_STRING);
        userDTO.setSalary(DEFAULT_INTEGER);
        userDTO.setGrade(DEFAULT_INTEGER);

        // When
        Set<ConstraintViolation<UserDTO>> violations = validator.validate(userDTO);

        // Then
        Assertions.assertFalse(violations.isEmpty());
        Assertions.assertEquals(ONE_CONSTRAINT_VIOLATION_EXPECTED, violations.size());
    }

    @Test
    void nullSurname_oneConstraintViolated() {
        // Given
        UserDTO userDTO = new UserDTO();
        userDTO.setId(DEFAULT_LONG);
        userDTO.setName(DEFAULT_STRING);
        userDTO.setSurname(null);
        userDTO.setSalary(DEFAULT_INTEGER);
        userDTO.setGrade(DEFAULT_INTEGER);

        // When
        Set<ConstraintViolation<UserDTO>> violations = validator.validate(userDTO);

        // Then
        Assertions.assertFalse(violations.isEmpty());
        Assertions.assertEquals(ONE_CONSTRAINT_VIOLATION_EXPECTED, violations.size());
    }

    @Test
    void emptySurname_oneConstraintViolated() {
        // Given
        UserDTO userDTO = new UserDTO();
        userDTO.setId(DEFAULT_LONG);
        userDTO.setName(DEFAULT_STRING);
        userDTO.setSurname(EMPTY_STRING);
        userDTO.setSalary(DEFAULT_INTEGER);
        userDTO.setGrade(DEFAULT_INTEGER);

        // When
        Set<ConstraintViolation<UserDTO>> violations = validator.validate(userDTO);

        // Then
        Assertions.assertFalse(violations.isEmpty());
        Assertions.assertEquals(ONE_CONSTRAINT_VIOLATION_EXPECTED, violations.size());
    }

    @Test
    void whitespacesAsSurname_oneConstraintViolated() {
        // Given
        UserDTO userDTO = new UserDTO();
        userDTO.setId(DEFAULT_LONG);
        userDTO.setName(DEFAULT_STRING);
        userDTO.setSurname(WHITESPACES_STRING);
        userDTO.setSalary(DEFAULT_INTEGER);
        userDTO.setGrade(DEFAULT_INTEGER);

        // When
        Set<ConstraintViolation<UserDTO>> violations = validator.validate(userDTO);

        // Then
        Assertions.assertFalse(violations.isEmpty());
        Assertions.assertEquals(ONE_CONSTRAINT_VIOLATION_EXPECTED, violations.size());
    }

    @Test
    void surnameLongerThan64Characters_oneConstraintViolated() {
        // Given
        UserDTO userDTO = new UserDTO();
        userDTO.setId(DEFAULT_LONG);
        userDTO.setName(DEFAULT_STRING);
        userDTO.setSurname(LENGTH_65_CHARACTERS_STRINGS);
        userDTO.setSalary(DEFAULT_INTEGER);
        userDTO.setGrade(DEFAULT_INTEGER);

        // When
        Set<ConstraintViolation<UserDTO>> violations = validator.validate(userDTO);

        // Then
        Assertions.assertFalse(violations.isEmpty());
        Assertions.assertEquals(ONE_CONSTRAINT_VIOLATION_EXPECTED, violations.size());
    }

    @Test
    void nullSalary_oneConstraintViolated() {
        // Given
        UserDTO userDTO = new UserDTO();
        userDTO.setId(DEFAULT_LONG);
        userDTO.setName(DEFAULT_STRING);
        userDTO.setSurname(DEFAULT_STRING);
        userDTO.setSalary(null);
        userDTO.setGrade(DEFAULT_INTEGER);

        // When
        Set<ConstraintViolation<UserDTO>> violations = validator.validate(userDTO);

        // Then
        Assertions.assertFalse(violations.isEmpty());
        Assertions.assertEquals(ONE_CONSTRAINT_VIOLATION_EXPECTED, violations.size());
    }

    @Test
    void nullGrade_oneConstraintViolated() {
        // Given
        UserDTO userDTO = new UserDTO();
        userDTO.setId(DEFAULT_LONG);
        userDTO.setName(DEFAULT_STRING);
        userDTO.setSurname(DEFAULT_STRING);
        userDTO.setSalary(DEFAULT_INTEGER);
        userDTO.setGrade(null);

        // When
        Set<ConstraintViolation<UserDTO>> violations = validator.validate(userDTO);

        // Then
        Assertions.assertFalse(violations.isEmpty());
        Assertions.assertEquals(ONE_CONSTRAINT_VIOLATION_EXPECTED, violations.size());
    }

    @Test
    void salaryValueZero_noConstraintsViolated() {
        // Given
        UserDTO userDTO = new UserDTO();
        userDTO.setId(DEFAULT_LONG);
        userDTO.setName(DEFAULT_STRING);
        userDTO.setSurname(DEFAULT_STRING);
        userDTO.setSalary(ZERO_INTEGER);
        userDTO.setGrade(DEFAULT_INTEGER);

        // When
        Set<ConstraintViolation<UserDTO>> violations = validator.validate(userDTO);

        // Then
        Assertions.assertTrue(violations.isEmpty());
    }

    @Test
    void gradeValueZero_noConstraintsViolated() {
        // Given
        UserDTO userDTO = new UserDTO();
        userDTO.setId(DEFAULT_LONG);
        userDTO.setName(DEFAULT_STRING);
        userDTO.setSurname(DEFAULT_STRING);
        userDTO.setSalary(DEFAULT_INTEGER);
        userDTO.setGrade(ZERO_INTEGER);

        // When
        Set<ConstraintViolation<UserDTO>> violations = validator.validate(userDTO);

        // Then
        Assertions.assertTrue(violations.isEmpty());
    }
}