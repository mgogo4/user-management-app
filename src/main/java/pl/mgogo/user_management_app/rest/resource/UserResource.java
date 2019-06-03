/*
 * Copyright (c) 2019 the original author or authors.
 */

package pl.mgogo.user_management_app.rest.resource;

import org.springframework.hateoas.ResourceSupport;
import pl.mgogo.user_management_app.dto.UserDTO;

import java.util.Objects;

/**
 * Created by IntelliJ IDEA.
 *
 * @author Michał Gogolewski
 */

public class UserResource extends ResourceSupport {
    private UserDTO user;

    public UserResource(UserDTO user) {
        this.user = user;
    }

    public UserDTO getUser() {
        return user;
    }

    public void setUser(UserDTO user) {
        this.user = user;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof UserResource)) {
            return false;
        }
        if (!super.equals(o)) {
            return false;
        }
        UserResource response = (UserResource) o;
        return Objects.equals(getUser(), response.getUser());
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getUser());
    }
}
