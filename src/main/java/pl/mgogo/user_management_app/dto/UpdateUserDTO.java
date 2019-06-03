/*
 * Copyright (c) 2019 the original author or authors.
 */

package pl.mgogo.user_management_app.dto;

import pl.mgogo.user_management_app.util.Constants;

import javax.validation.constraints.Size;
import java.io.Serializable;

/**
 * Created by IntelliJ IDEA.
 *
 * @author Michał Gogolewski
 */
public class UpdateUserDTO implements Serializable {

    private static final long serialVersionUID = 3159448584528084572L;

    @Size(max = 64, message = Constants.Message.ERROR_USER_NAME_EXCEEDS_64)
    private String name;

    @Size(max = 64, message = Constants.Message.ERROR_USER_SURNAME_EXCEEDS_64)
    private String surname;

    private Integer grade;

    private Integer salary;

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
