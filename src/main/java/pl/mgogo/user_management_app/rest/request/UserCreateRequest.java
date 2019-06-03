/*
 * Copyright (c) 2019 the original author or authors.
 */

package pl.mgogo.user_management_app.rest.request;

import pl.mgogo.user_management_app.dto.NewUserDTO;
import pl.mgogo.user_management_app.util.Constants;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

/**
 * Created by IntelliJ IDEA.
 *
 * @author Michał Gogolewski
 */
public class UserCreateRequest implements BaseRequest {
    private static final long serialVersionUID = -2962713586182450909L;

    @Valid
    @NotNull(message = Constants.Message.ERROR_CREATE_USER_REQUEST_EMPTY)
    private NewUserDTO user;

    public NewUserDTO getUser() {
        return user;
    }

    public void setUser(NewUserDTO user) {
        this.user = user;
    }
}
