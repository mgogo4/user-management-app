/*
 * Copyright (c) 2019 the original author or authors.
 */

package pl.mgogo.user_management_app.dto;

import pl.mgogo.user_management_app.util.Constants;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;
import javax.validation.constraints.Size;
import java.io.Serializable;

/**
 * Created by IntelliJ IDEA.
 *
 * @author Michał Gogolewski
 */
public class UserDTO implements Serializable {
    private static final long serialVersionUID = 4780540276677670549L;

    @NotNull(message = Constants.Message.ERROR_USER_ID_NULL)
    @PositiveOrZero(message = Constants.Message.ERROR_USER_ID_NEGATIVE)
    private Long id;

    @NotBlank(message = Constants.Message.ERROR_USER_NAME_BLANK)
    @Size(max = 64, message = Constants.Message.ERROR_USER_NAME_EXCEEDS_64)
    private String name;

    @NotBlank(message = Constants.Message.ERROR_USER_SURNAME_BLANK)
    @Size(max = 64, message = Constants.Message.ERROR_USER_SURNAME_EXCEEDS_64)
    private String surname;

    @NotNull(message = Constants.Message.ERROR_USER_GRADE_NULL)
    private Integer grade;

    @NotNull(message = Constants.Message.ERROR_USER_SALARY_NULL)
    private Integer salary;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public Integer getGrade() {
        return grade;
    }

    public void setGrade(Integer grade) {
        this.grade = grade;
    }

    public Integer getSalary() {
        return salary;
    }

    public void setSalary(Integer salary) {
        this.salary = salary;
    }
}
