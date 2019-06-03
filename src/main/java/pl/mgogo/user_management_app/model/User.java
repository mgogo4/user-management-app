/*
 * Copyright (c) 2019 the original author or authors.
 */

package pl.mgogo.user_management_app.model;

import pl.mgogo.user_management_app.util.Constants;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * User entity is the main application entity. Maps database user table.
 *
 * @author Michał Gogolewski
 */
@Entity
public class User extends AbstractEntity {

    private static final long serialVersionUID = 3545997602921861935L;

    /**
     * User first name together with surname allows to identify the user.
     * Each user must have a specified name no longer than 64 characters.
     */
    @Column
    @Size(max = 64, message = Constants.Message.ERROR_USER_NAME_EXCEEDS_64)
    @NotBlank(message = Constants.Message.ERROR_USER_NAME_BLANK)
    private String name;

    /**
     * User family name together with name allows to identify the user.
     * Each user must have a specified surname no longer than 64 characters.
     */
    @Column
    @Size(max = 64, message = Constants.Message.ERROR_USER_SURNAME_EXCEEDS_64)
    @NotBlank(message = Constants.Message.ERROR_USER_SURNAME_BLANK)
    private String surname;

    /**
     * Actual user grade, indicates skills and user advancement.
     * The higher the grade, the more advanced and skilled the user.
     * Each user must have a specified grade.
     */
    @Column
    @NotNull(message = Constants.Message.ERROR_USER_GRADE_NULL)
    private Integer grade;

    /**
     * Actual monthly salary for the work that the user receives.
     * Each user must have a specified salary.
     */
    @Column
    @NotNull(message = Constants.Message.ERROR_USER_SALARY_NULL)
    private Integer salary;

    /**
     * Instantiates a new User.
     */
    public User() {
    }

    /**
     * Instantiates a new User.
     *
     * @param name    the name
     * @param surname the surname
     * @param grade   the grade
     * @param salary  the salary
     */
    public User(String name, String surname, Integer grade, Integer salary) {
        this.name = name;
        this.surname = surname;
        this.grade = grade;
        this.salary = salary;
    }

    /**
     * Gets name.
     *
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * Sets name.
     *
     * @param name the name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets surname.
     *
     * @return the surname
     */
    public String getSurname() {
        return surname;
    }

    /**
     * Sets surname.
     *
     * @param surname the surname
     */
    public void setSurname(String surname) {
        this.surname = surname;
    }

    /**
     * Gets grade.
     *
     * @return the grade
     */
    public Integer getGrade() {
        return grade;
    }

    /**
     * Sets grade.
     *
     * @param grade the grade
     */
    public void setGrade(Integer grade) {
        this.grade = grade;
    }

    /**
     * Gets salary.
     *
     * @return the salary
     */
    public Integer getSalary() {
        return salary;
    }

    /**
     * Sets salary.
     *
     * @param salary the salary
     */
    public void setSalary(Integer salary) {
        this.salary = salary;
    }
}
