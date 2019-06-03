/*
 * Copyright (c) 2019 the original author or authors.
 */

package pl.mgogo.user_management_app.rest.assembler;

import org.springframework.hateoas.ResourceAssembler;
import org.springframework.stereotype.Component;
import pl.mgogo.user_management_app.dto.UserDTO;
import pl.mgogo.user_management_app.rest.controller.impl.UserControllerImpl;
import pl.mgogo.user_management_app.rest.resource.UserResource;
import pl.mgogo.user_management_app.util.Constants;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

/**
 * Created by IntelliJ IDEA.
 *
 * @author Michał Gogolewski
 */
@Component
public class UserAssembler implements ResourceAssembler<UserDTO, UserResource> {

    @Override
    public UserResource toResource(UserDTO user) {
        final UserResource resource = new UserResource(user);

        // Add self link, action links and link to users list
        resource.add(linkTo(methodOn(UserControllerImpl.class, user.getId()).getUser(user.getId())).withSelfRel(),
                linkTo(methodOn(UserControllerImpl.class, user.getId()).deleteUser(user.getId())).withRel(Constants.Path.DELETE),
                linkTo(methodOn(UserControllerImpl.class, user.getId()).updateUser(user.getId(), null)).withRel(Constants.Path.UPDATE),
                linkTo(UserControllerImpl.class).withRel(Constants.Path.USERS));

        return resource;
    }

}
