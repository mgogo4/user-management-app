/*
 * Copyright (c) 2019 the original author or authors.
 */

package pl.mgogo.user_management_app.rest.controller.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.ExposesResourceFor;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.PagedResources;
import org.springframework.hateoas.UriTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;
import pl.mgogo.user_management_app.dto.UserDTO;
import pl.mgogo.user_management_app.model.User;
import pl.mgogo.user_management_app.rest.assembler.UserAssembler;
import pl.mgogo.user_management_app.rest.controller.UserController;
import pl.mgogo.user_management_app.rest.exception.UserNotFoundException;
import pl.mgogo.user_management_app.rest.request.UserCreateRequest;
import pl.mgogo.user_management_app.rest.request.UserUpdateRequest;
import pl.mgogo.user_management_app.rest.resource.UserResource;
import pl.mgogo.user_management_app.service.UserService;
import pl.mgogo.user_management_app.util.Constants;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import java.net.URI;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

/**
 * Created by IntelliJ IDEA.
 *
 * @author Michał Gogolewski
 */
@RestController
@ExposesResourceFor(User.class)
public class UserControllerImpl implements UserController {
    private static final Logger LOGGER = LoggerFactory.getLogger(UserControllerImpl.class);

    private final UserService service;
    private final UserAssembler assembler;
    private final PagedResourcesAssembler<UserDTO> pagedResourcesAssembler;


    @Autowired
    public UserControllerImpl(final UserService service, final UserAssembler assembler,
                              final PagedResourcesAssembler<UserDTO> pagedResourcesAssembler) {
        this.service = service;
        this.assembler = assembler;
        this.pagedResourcesAssembler = pagedResourcesAssembler;
    }

    @Override
    public ResponseEntity<UserResource> createUser(@Valid UserCreateRequest request) {
        LOGGER.info("Received POST request. Creating new user.");
        final UserDTO userDTO = service.create(request.getUser());
        final UserResource resource = assembler.toResource(userDTO);

        LOGGER.info("Successfully created user with id={}.", userDTO.getId());
        return ResponseEntity
                .created(URI.create(resource.getId().expand().getHref()))
                .body(resource);
    }

    @Override
    public ResponseEntity<UserResource> getUser(@Min(value = 0, message = Constants.Message.ERROR_USER_ID_NEGATIVE) Long id) {
        LOGGER.info("Received GET request. Retrieving user with id={}.", id);

        final UserDTO userDTO = service.findOne(id).orElseThrow(() -> new UserNotFoundException(id));
        final UserResource resource = assembler.toResource(userDTO);

        LOGGER.info("Successfully retrieved user with id={}.", id);
        return ResponseEntity.ok(resource);
    }

    @Override
    public ResponseEntity<UserResource> updateUser(@Min(value = 0,
            message = Constants.Message.ERROR_USER_ID_NEGATIVE) Long id,
                                                   @Valid UserUpdateRequest request) {
        LOGGER.info("Received PUT request. Updating user with id={}.", id);

        final UserDTO userDTO = service.update(id, request.getUser()).orElseThrow(() -> new UserNotFoundException(id));
        final UserResource resource = assembler.toResource(userDTO);

        LOGGER.info("Successfully updated user with id={}.", id);
        return ResponseEntity
                .created(URI.create(resource.getId().expand().getHref()))
                .body(resource);
    }

    @Override
    public ResponseEntity deleteUser(@Min(value = 0, message = Constants.Message.ERROR_USER_ID_NEGATIVE) Long id) {
        LOGGER.info("Received DELETE request. Deleting user with id={}.", id);
        if (!service.delete(id)) {
            throw new UserNotFoundException(id);
        }

        LOGGER.info("Successfully deleted user with id={}.", id);
        return ResponseEntity.noContent().build();
    }

    @Override
    public ResponseEntity<PagedResources<UserResource>> searchUsers(
            @RequestParam(value = "search", required = false) String search,
            Pageable pageable) {

        LOGGER.info("Received GET search request. Searching for users.");
        final Page<UserDTO> userDTOsPage = service.findAll(search, pageable);


        final PagedResources<UserResource> resource = pagedResourcesAssembler.toResource(userDTOsPage, assembler,
                buildSearchUsersSelfLink(userDTOsPage, search));

        resource.add(linkTo(UserController.class).withRel(Constants.Path.USERS));

        LOGGER.info("Retrieved users matching search criteria.");
        return new ResponseEntity<>(resource, HttpStatus.OK);
    }

    private Link buildSearchUsersSelfLink(Page<UserDTO> page, String search) {
        final UriComponentsBuilder builder =
                linkTo(methodOn(UserController.class).searchUsers(search, page.getPageable())).toUriComponentsBuilder();

        final Sort sort = page.getPageable().getSort();

        builder.replaceQueryParam(Constants.Path.PARAM_SEARCH, search);
        builder.replaceQueryParam(Constants.Path.PARAM_PAGE, page.getPageable().getPageNumber());
        builder.replaceQueryParam(Constants.Path.PARAM_SIZE, page.getPageable().getPageSize());

        if (sort.isSorted()) {
            builder.replaceQueryParam(Constants.Path.PARAM_SORT,
                    sort.stream().map(order -> order.toString().replace(Constants.Sort.ORDER_DELIMITER,
                            Constants.Sort.URI_ORDER_DELIMITER).toLowerCase()).toArray());
        }
        return new Link(new UriTemplate(builder.build().toString()), Link.REL_SELF);
    }
}
