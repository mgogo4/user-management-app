/*
 * Copyright (c) 2019 the original author or authors.
 */

package pl.mgogo.user_management_app.dto.mapper;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import pl.mgogo.user_management_app.dto.NewUserDTO;
import pl.mgogo.user_management_app.dto.mapper.config.MappersTestConfig;
import pl.mgogo.user_management_app.model.User;

import java.util.Arrays;
import java.util.List;

/**
 * {@link NewUserDTOMapper} unit test class.
 *
 * @author Michał Gogolewski
 */
@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = MappersTestConfig.class)
public class NewUserDTOMapperTest {

    private final static Long DEFAULT_ID = 1L;
    private final static Long DEFAULT_ID_1 = 2L;
    private final static Integer DEFAULT_VERSION = 4;
    private final static Integer DEFAULT_VERSION_1 = 5;
    private final static String DEFAULT_NAME = "Name";
    private final static String DEFAULT_NAME_1 = "Name 1";
    private final static String DEFAULT_SURNAME = "Surname";
    private final static String DEFAULT_SURNAME_1 = "Surname 1";
    private final static Integer DEFAULT_SALARY = 1000;
    private final static Integer DEFAULT_SALARY_1 = 2000;
    private final static Integer DEFAULT_GRADE = 1;
    private final static Integer DEFAULT_GRADE_1 = 2;
    @Autowired
    private NewUserDTOMapper mapper;

    @Test
    public void toModel_returnsCorrectModel() {
        // Given
        NewUserDTO dto = new NewUserDTO();
        dto.setName(DEFAULT_NAME);
        dto.setSurname(DEFAULT_SURNAME);
        dto.setSalary(DEFAULT_SALARY);
        dto.setGrade(DEFAULT_GRADE);

        // When
        User model = mapper.toModel(dto);

        // Then
        assertEquals(dto, model);
    }


    @Test
    public void toModelList_returnsCorrectModel() {
        // Given
        NewUserDTO dto = new NewUserDTO();
        dto.setName(DEFAULT_NAME);
        dto.setSurname(DEFAULT_SURNAME);
        dto.setSalary(DEFAULT_SALARY);
        dto.setGrade(DEFAULT_GRADE);

        NewUserDTO dto_1 = new NewUserDTO();
        dto_1.setName(DEFAULT_NAME_1);
        dto_1.setSurname(DEFAULT_SURNAME_1);
        dto_1.setSalary(DEFAULT_SALARY_1);
        dto_1.setGrade(DEFAULT_GRADE_1);

        // When
        List<User> models = mapper.toModelList(Arrays.asList(dto, dto_1));

        // Then
        Assertions.assertNotNull(models);
        assertEquals(dto, models.get(0));
        assertEquals(dto_1, models.get(1));
    }

    @Test
    public void toDTO_returnsCorrectModel() {
        // Given
        User model = new User();
        model.setId(DEFAULT_ID);
        model.setName(DEFAULT_NAME);
        model.setSurname(DEFAULT_SURNAME);
        model.setSalary(DEFAULT_SALARY);
        model.setGrade(DEFAULT_GRADE);

        // When
        NewUserDTO dto = mapper.toDTO(model);

        // Then
        assertEquals(model, dto);
    }


    @Test
    public void toDTOList_returnsCorrectModel() {
        // Given
        User model = new User();
        model.setId(DEFAULT_ID);
        model.setVersion(DEFAULT_VERSION);
        model.setName(DEFAULT_NAME);
        model.setSurname(DEFAULT_SURNAME);
        model.setSalary(DEFAULT_SALARY);
        model.setGrade(DEFAULT_GRADE);

        User model_1 = new User();
        model_1.setId(DEFAULT_ID_1);
        model_1.setVersion(DEFAULT_VERSION_1);
        model_1.setName(DEFAULT_NAME_1);
        model_1.setSurname(DEFAULT_SURNAME_1);
        model_1.setSalary(DEFAULT_SALARY_1);
        model_1.setGrade(DEFAULT_GRADE_1);

        // When
        List<NewUserDTO> dtos = mapper.toDTOList(Arrays.asList(model, model_1));

        // Then
        Assertions.assertNotNull(dtos);
        assertEquals(model, dtos.get(0));
        assertEquals(model_1, dtos.get(1));
    }

    private void assertEquals(NewUserDTO expected, User actual) {
        Assertions.assertNotNull(actual);
        Assertions.assertNotNull(actual.getName());
        Assertions.assertNotNull(actual.getSurname());
        Assertions.assertNotNull(actual.getSalary());
        Assertions.assertNotNull(actual.getGrade());

        Assertions.assertNull(actual.getId());
        Assertions.assertNull(actual.getVersion());

        Assertions.assertEquals(expected.getName(), actual.getName());
        Assertions.assertEquals(expected.getSurname(), actual.getSurname());
        Assertions.assertEquals(expected.getSalary(), actual.getSalary());
        Assertions.assertEquals(expected.getGrade(), actual.getGrade());
    }

    private void assertEquals(User expected, NewUserDTO actual) {
        Assertions.assertNotNull(actual);
        Assertions.assertNotNull(actual.getName());
        Assertions.assertNotNull(actual.getSurname());
        Assertions.assertNotNull(actual.getSalary());
        Assertions.assertNotNull(actual.getGrade());

        Assertions.assertEquals(expected.getName(), actual.getName());
        Assertions.assertEquals(expected.getSurname(), actual.getSurname());
        Assertions.assertEquals(expected.getSalary(), actual.getSalary());
        Assertions.assertEquals(expected.getGrade(), actual.getGrade());
    }

}