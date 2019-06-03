/*
 * Copyright (c) 2019 the original author or authors.
 */

package pl.mgogo.user_management_app.rest.controller;

import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.PagedResources;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pl.mgogo.user_management_app.rest.request.UserCreateRequest;
import pl.mgogo.user_management_app.rest.request.UserUpdateRequest;
import pl.mgogo.user_management_app.rest.resource.UserResource;
import pl.mgogo.user_management_app.util.Constants;

import javax.validation.Valid;
import javax.validation.constraints.Min;

/**
 * Created by IntelliJ IDEA.
 *
 * @author Michał Gogolewski
 */
@RequestMapping(path = Constants.Path.USERS, produces = MediaType.APPLICATION_JSON_VALUE)
public interface UserController {

    @PostMapping
    ResponseEntity<UserResource> createUser(@Valid @RequestBody UserCreateRequest request);

    @GetMapping(path = Constants.Path.ID)
    ResponseEntity<UserResource> getUser(
            @PathVariable @Min(value = 0, message = Constants.Message.ERROR_USER_ID_NEGATIVE) Long id);

    @PutMapping(path = Constants.Path.ID)
    ResponseEntity<UserResource> updateUser(
            @PathVariable @Min(value = 0, message = Constants.Message.ERROR_USER_ID_NEGATIVE) Long id,
            @Valid @RequestBody UserUpdateRequest request);

    @DeleteMapping(path = Constants.Path.ID)
    ResponseEntity deleteUser(@PathVariable @Min(value = 0, message = Constants.Message.ERROR_USER_ID_NEGATIVE) Long id);

    @GetMapping
    ResponseEntity<PagedResources<UserResource>> searchUsers(@RequestParam(value = "search", required = false) String search,
                                                             Pageable pageable);
}
