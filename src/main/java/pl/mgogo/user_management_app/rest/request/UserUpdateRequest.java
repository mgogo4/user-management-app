/*
 * Copyright (c) 2019 the original author or authors.
 */

package pl.mgogo.user_management_app.rest.request;

import pl.mgogo.user_management_app.dto.UpdateUserDTO;
import pl.mgogo.user_management_app.util.Constants;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

/**
 * Created by IntelliJ IDEA.
 *
 * @author Michał Gogolewski
 */
public class UserUpdateRequest implements BaseRequest {
    private static final long serialVersionUID = 2847551799113933051L;

    @Valid
    @NotNull(message = Constants.Message.ERROR_UPDATE_USER_REQUEST_EMPTY)
    private UpdateUserDTO user;

    public UpdateUserDTO getUser() {
        return user;
    }

    public void setUser(UpdateUserDTO user) {
        this.user = user;
    }
}
