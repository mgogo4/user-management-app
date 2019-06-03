/*
 * Copyright (c) 2019 the original author or authors.
 */

package pl.mgogo.user_management_app.rest.exception;

import pl.mgogo.user_management_app.util.Constants;

/**
 * Created by IntelliJ IDEA.
 *
 * @author Michał Gogolewski
 */
public class UserNotFoundException extends RuntimeException {

    private final Long userId;

    public UserNotFoundException(Long id) {
        super(Constants.Message.EXCEPTION_USER_NOT_FOUND);
        this.userId = id;
    }

    public Long getUserId() {
        return userId;
    }
}
