/*
 * Copyright (c) 2019 the original author or authors.
 */

package pl.mgogo.user_management_app.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import pl.mgogo.user_management_app.dto.NewUserDTO;
import pl.mgogo.user_management_app.dto.UpdateUserDTO;
import pl.mgogo.user_management_app.dto.UserDTO;

import java.util.Optional;

/**
 * Service Interface for managing {@link pl.mgogo.user_management_app.model.User} entities.
 * Accepts and returns DTO maps them to entities to perform database operations.
 *
 * @author Michał Gogolewski
 */
public interface UserService {

    /**
     * Creates {@link pl.mgogo.user_management_app.model.User} entity from given {@link NewUserDTO}.
     * Maps saved entity to {@link UserDTO} and returns.
     *
     * @param newUserDTO DTO that holds the data to create a new entity.
     * @return DTO that holds the data of new created entity.
     */
    UserDTO create(NewUserDTO newUserDTO);

    /**
     * Updates {@link pl.mgogo.user_management_app.model.User} entity matching given ID with data from the
     * {@link UpdateUserDTO}.
     * Maps saved entity to {@link UserDTO} and returns as optional.
     *
     * @param id            the ID of entity to update.
     * @param updateUserDTO DTO that holds the data to update entity.
     * @return Optional DTO that holds the data of updated entity. If entity does not exist returns empty.
     */
    Optional<UserDTO> update(Long id, UpdateUserDTO updateUserDTO);

    /**
     * Finds {@link pl.mgogo.user_management_app.model.User} entity matching given ID.
     * Maps retrieved entity to {@link UserDTO} and returns as optional.
     *
     * @param id the ID of searched entity.
     * @return Optional DTO that holds the data of searched entity. If entity does not exist returns empty.
     */
    Optional<UserDTO> findOne(Long id);

    /**
     * Finds all {@link pl.mgogo.user_management_app.model.User} entities matching given search criteria.
     * Search criteria are given as a string formatted as rsql. Rsql is converted to Spring Specifications and
     * matching it entities are retrieved from data source filing page as specified in given page request.
     * Retrieved entities are mapped to {@link UserDTO} and returned as page.
     *
     * @param rsql     searching criteria string in rsql format.
     * @param pageable page request.
     * @return Page of entities mapped to {@link UserDTO} matching given search criteria.
     * @see <a href="https://github.com/jirutka/rsql-parser">RSQL / FIQL parser</a>
     */
    Page<UserDTO> findAll(String rsql, Pageable pageable);

    /**
     * Deletes {@link pl.mgogo.user_management_app.model.User} entity matching given ID.
     * Returns the boolean value confirming the deletion of the entity.
     * Value false means that there was no entity of given ID.
     *
     * @param id the ID of entity to delete.
     * @return true if found and deleted entity of given ID. false if entity scheduled to delete does not exist.
     */
    boolean delete(Long id);
}
