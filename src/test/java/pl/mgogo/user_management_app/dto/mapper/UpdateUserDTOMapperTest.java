/*
 * Copyright (c) 2019 the original author or authors.
 */

package pl.mgogo.user_management_app.dto.mapper;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import pl.mgogo.user_management_app.dto.UpdateUserDTO;
import pl.mgogo.user_management_app.dto.mapper.config.MappersTestConfig;
import pl.mgogo.user_management_app.model.User;

/**
 * {@link UpdateUserDTOMapper} unit test class.
 *
 * @author Michał Gogolewski
 */
@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = MappersTestConfig.class)
public class UpdateUserDTOMapperTest {

    private final static Long DEFAULT_ID = 1L;
    private final static Integer DEFAULT_VERSION = 4;
    private final static String DEFAULT_NAME = "Name";
    private final static String DEFAULT_NAME_1 = "Name 1";
    private final static String DEFAULT_SURNAME = "Surname";
    private final static String DEFAULT_SURNAME_1 = "Surname 1";
    private final static Integer DEFAULT_SALARY = 1000;
    private final static Integer DEFAULT_SALARY_1 = 2000;
    private final static Integer DEFAULT_GRADE = 1;
    private final static Integer DEFAULT_GRADE_1 = 2;
    private final static String EMPTY_STRING = "";
    private final static String WHITESPACES_STRING = "   ";

    @Autowired
    private UpdateUserDTOMapper mapper;

    private User user;

    @BeforeEach
    void setUp() {
        user = new User();
        user.setId(DEFAULT_ID);
        user.setVersion(DEFAULT_VERSION);
        user.setName(DEFAULT_NAME);
        user.setSurname(DEFAULT_SURNAME);
        user.setSalary(DEFAULT_SALARY);
        user.setGrade(DEFAULT_GRADE);
    }

    @Test
    public void updateModel_noneField_modelIsNotChanged() {
        // Given
        UpdateUserDTO dto = new UpdateUserDTO();

        // When
        mapper.updateModel(dto, user);

        // Then
        Assertions.assertNotNull(user);
        Assertions.assertNotNull(user.getId());
        Assertions.assertNotNull(user.getVersion());
        Assertions.assertNotNull(user.getName());
        Assertions.assertNotNull(user.getSurname());
        Assertions.assertNotNull(user.getSalary());
        Assertions.assertNotNull(user.getGrade());

        Assertions.assertEquals(user.getId(), DEFAULT_ID);
        Assertions.assertEquals(user.getVersion(), DEFAULT_VERSION);
        Assertions.assertEquals(user.getName(), DEFAULT_NAME);
        Assertions.assertEquals(user.getSurname(), DEFAULT_SURNAME);
        Assertions.assertEquals(user.getSalary(), DEFAULT_SALARY);
        Assertions.assertEquals(user.getGrade(), DEFAULT_GRADE);
    }

    @Test
    public void updateModel_name_modelHasUpdatedName() {
        // Given
        UpdateUserDTO dto = new UpdateUserDTO();
        dto.setName(DEFAULT_NAME_1);

        // When
        mapper.updateModel(dto, user);

        // Then
        Assertions.assertNotNull(user);
        Assertions.assertNotNull(user.getId());
        Assertions.assertNotNull(user.getVersion());
        Assertions.assertNotNull(user.getName());
        Assertions.assertNotNull(user.getSurname());
        Assertions.assertNotNull(user.getSalary());
        Assertions.assertNotNull(user.getGrade());

        Assertions.assertEquals(user.getId(), DEFAULT_ID);
        Assertions.assertEquals(user.getVersion(), DEFAULT_VERSION);
        Assertions.assertEquals(user.getName(), DEFAULT_NAME_1);
        Assertions.assertEquals(user.getSurname(), DEFAULT_SURNAME);
        Assertions.assertEquals(user.getSalary(), DEFAULT_SALARY);
        Assertions.assertEquals(user.getGrade(), DEFAULT_GRADE);
    }

    @Test
    public void updateModel_surname_modelHasUpdatedSurname() {
        // Given
        UpdateUserDTO dto = new UpdateUserDTO();
        dto.setSurname(DEFAULT_SURNAME_1);

        // When
        mapper.updateModel(dto, user);

        // Then
        Assertions.assertNotNull(user);
        Assertions.assertNotNull(user.getId());
        Assertions.assertNotNull(user.getVersion());
        Assertions.assertNotNull(user.getName());
        Assertions.assertNotNull(user.getSurname());
        Assertions.assertNotNull(user.getSalary());
        Assertions.assertNotNull(user.getGrade());

        Assertions.assertEquals(user.getId(), DEFAULT_ID);
        Assertions.assertEquals(user.getVersion(), DEFAULT_VERSION);
        Assertions.assertEquals(user.getName(), DEFAULT_NAME);
        Assertions.assertEquals(user.getSurname(), DEFAULT_SURNAME_1);
        Assertions.assertEquals(user.getSalary(), DEFAULT_SALARY);
        Assertions.assertEquals(user.getGrade(), DEFAULT_GRADE);
    }

    @Test
    public void updateModel_salary_modelHasUpdatedSalary() {
        // Given
        UpdateUserDTO dto = new UpdateUserDTO();
        dto.setSalary(DEFAULT_SALARY_1);

        // When
        mapper.updateModel(dto, user);

        // Then
        Assertions.assertNotNull(user);
        Assertions.assertNotNull(user.getId());
        Assertions.assertNotNull(user.getVersion());
        Assertions.assertNotNull(user.getName());
        Assertions.assertNotNull(user.getSurname());
        Assertions.assertNotNull(user.getSalary());
        Assertions.assertNotNull(user.getGrade());

        Assertions.assertEquals(user.getId(), DEFAULT_ID);
        Assertions.assertEquals(user.getVersion(), DEFAULT_VERSION);
        Assertions.assertEquals(user.getName(), DEFAULT_NAME);
        Assertions.assertEquals(user.getSurname(), DEFAULT_SURNAME);
        Assertions.assertEquals(user.getSalary(), DEFAULT_SALARY_1);
        Assertions.assertEquals(user.getGrade(), DEFAULT_GRADE);
    }

    @Test
    public void updateModel_grade_modelHasUpdatedGrade() {
        // Given
        UpdateUserDTO dto = new UpdateUserDTO();
        dto.setGrade(DEFAULT_GRADE_1);

        // When
        mapper.updateModel(dto, user);

        // Then
        Assertions.assertNotNull(user);
        Assertions.assertNotNull(user.getId());
        Assertions.assertNotNull(user.getVersion());
        Assertions.assertNotNull(user.getName());
        Assertions.assertNotNull(user.getSurname());
        Assertions.assertNotNull(user.getSalary());
        Assertions.assertNotNull(user.getGrade());

        Assertions.assertEquals(user.getId(), DEFAULT_ID);
        Assertions.assertEquals(user.getVersion(), DEFAULT_VERSION);
        Assertions.assertEquals(user.getName(), DEFAULT_NAME);
        Assertions.assertEquals(user.getSurname(), DEFAULT_SURNAME);
        Assertions.assertEquals(user.getSalary(), DEFAULT_SALARY);
        Assertions.assertEquals(user.getGrade(), DEFAULT_GRADE_1);
    }

    @Test
    public void updateModel_emptyName_modelIsNotChanged() {
        // Given
        UpdateUserDTO dto = new UpdateUserDTO();
        dto.setName(EMPTY_STRING);

        // When
        mapper.updateModel(dto, user);

        // Then
        Assertions.assertNotNull(user);
        Assertions.assertNotNull(user.getId());
        Assertions.assertNotNull(user.getVersion());
        Assertions.assertNotNull(user.getName());
        Assertions.assertNotNull(user.getSurname());
        Assertions.assertNotNull(user.getSalary());
        Assertions.assertNotNull(user.getGrade());

        Assertions.assertEquals(user.getId(), DEFAULT_ID);
        Assertions.assertEquals(user.getVersion(), DEFAULT_VERSION);
        Assertions.assertEquals(user.getName(), DEFAULT_NAME);
        Assertions.assertEquals(user.getSurname(), DEFAULT_SURNAME);
        Assertions.assertEquals(user.getSalary(), DEFAULT_SALARY);
        Assertions.assertEquals(user.getGrade(), DEFAULT_GRADE);
    }

    @Test
    public void updateModel_emptySurname_modelIsNotChanged() {
        // Given
        UpdateUserDTO dto = new UpdateUserDTO();
        dto.setSurname(EMPTY_STRING);

        // When
        mapper.updateModel(dto, user);

        // Then
        Assertions.assertNotNull(user);
        Assertions.assertNotNull(user.getId());
        Assertions.assertNotNull(user.getVersion());
        Assertions.assertNotNull(user.getName());
        Assertions.assertNotNull(user.getSurname());
        Assertions.assertNotNull(user.getSalary());
        Assertions.assertNotNull(user.getGrade());

        Assertions.assertEquals(user.getId(), DEFAULT_ID);
        Assertions.assertEquals(user.getVersion(), DEFAULT_VERSION);
        Assertions.assertEquals(user.getName(), DEFAULT_NAME);
        Assertions.assertEquals(user.getSurname(), DEFAULT_SURNAME);
        Assertions.assertEquals(user.getSalary(), DEFAULT_SALARY);
        Assertions.assertEquals(user.getGrade(), DEFAULT_GRADE);
    }

    @Test
    public void updateModel_whitespacesName_modelIsNotChanged() {
        // Given
        UpdateUserDTO dto = new UpdateUserDTO();
        dto.setName(WHITESPACES_STRING);

        // When
        mapper.updateModel(dto, user);

        // Then
        Assertions.assertNotNull(user);
        Assertions.assertNotNull(user.getId());
        Assertions.assertNotNull(user.getVersion());
        Assertions.assertNotNull(user.getName());
        Assertions.assertNotNull(user.getSurname());
        Assertions.assertNotNull(user.getSalary());
        Assertions.assertNotNull(user.getGrade());

        Assertions.assertEquals(user.getId(), DEFAULT_ID);
        Assertions.assertEquals(user.getVersion(), DEFAULT_VERSION);
        Assertions.assertEquals(user.getName(), DEFAULT_NAME);
        Assertions.assertEquals(user.getSurname(), DEFAULT_SURNAME);
        Assertions.assertEquals(user.getSalary(), DEFAULT_SALARY);
        Assertions.assertEquals(user.getGrade(), DEFAULT_GRADE);
    }

    @Test
    public void updateModel_whitespacesSurname_modelIsNotChanged() {
        // Given
        UpdateUserDTO dto = new UpdateUserDTO();
        dto.setSurname(EMPTY_STRING);

        // When
        mapper.updateModel(dto, user);

        // Then
        Assertions.assertNotNull(user);
        Assertions.assertNotNull(user.getId());
        Assertions.assertNotNull(user.getVersion());
        Assertions.assertNotNull(user.getName());
        Assertions.assertNotNull(user.getSurname());
        Assertions.assertNotNull(user.getSalary());
        Assertions.assertNotNull(user.getGrade());

        Assertions.assertEquals(user.getId(), DEFAULT_ID);
        Assertions.assertEquals(user.getVersion(), DEFAULT_VERSION);
        Assertions.assertEquals(user.getName(), DEFAULT_NAME);
        Assertions.assertEquals(user.getSurname(), DEFAULT_SURNAME);
        Assertions.assertEquals(user.getSalary(), DEFAULT_SALARY);
        Assertions.assertEquals(user.getGrade(), DEFAULT_GRADE);
    }

    @Test
    public void updateModel_allFields_returnsCorrectModel() {
        UpdateUserDTO dto = new UpdateUserDTO();
        dto.setName(DEFAULT_NAME_1);
        dto.setSurname(DEFAULT_SURNAME_1);
        dto.setSalary(DEFAULT_SALARY_1);
        dto.setGrade(DEFAULT_GRADE_1);

        // When
        mapper.updateModel(dto, user);

        // Then
        Assertions.assertNotNull(user);
        Assertions.assertNotNull(user.getId());
        Assertions.assertNotNull(user.getVersion());
        Assertions.assertNotNull(user.getName());
        Assertions.assertNotNull(user.getSurname());
        Assertions.assertNotNull(user.getSalary());
        Assertions.assertNotNull(user.getGrade());

        Assertions.assertEquals(user.getId(), DEFAULT_ID);
        Assertions.assertEquals(user.getVersion(), DEFAULT_VERSION);
        Assertions.assertEquals(user.getName(), DEFAULT_NAME_1);
        Assertions.assertEquals(user.getSurname(), DEFAULT_SURNAME_1);
        Assertions.assertEquals(user.getSalary(), DEFAULT_SALARY_1);
        Assertions.assertEquals(user.getGrade(), DEFAULT_GRADE_1);
    }
}