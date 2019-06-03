/*
 * Copyright (c) 2019 the original author or authors.
 */

package pl.mgogo.user_management_app.util;

/**
 * Application constants collection.
 *
 * @author Michał Gogolewski
 */
public final class Constants {
    private Constants() {
    }

    /**
     * Path connected constants.
     */
    public static final class Sort {
        public static final String ORDER_DELIMITER = ": ";
        public static final String URI_ORDER_DELIMITER = ",";

        private Sort() {
        }
    }

    /**
     * Path connected constants.
     */
    public static final class Path {
        public static final String ID = "/{id}";
        public static final String USERS = "users";
        public static final String DELETE = "delete";
        public static final String UPDATE = "update";
        public static final String PARAM_SIZE = "size";
        public static final String PARAM_PAGE = "page";
        public static final String PARAM_SORT = "sort";
        public static final String PARAM_SEARCH = "search";

        private Path() {
        }
    }

    /**
     * Localized messages property names.
     */
    public static final class Message {
        public static final String ERROR_USER_NAME_BLANK = "{error.user.name.blank}";
        public static final String ERROR_USER_NAME_EXCEEDS_64 = "{error.user.name.longerThan64}";
        public static final String ERROR_USER_SURNAME_BLANK = "{error.user.surname.blank}";
        public static final String ERROR_USER_SURNAME_EXCEEDS_64 = "{error.user.surname.longerThan64}";
        public static final String ERROR_USER_GRADE_NULL = "{error.user.grade.null}";
        public static final String ERROR_USER_SALARY_NULL = "{error.user.salary.null}";
        public static final String ERROR_USER_ID_NULL = "{error.user.id.null}";
        public static final String ERROR_USER_ID_NEGATIVE = "{error.user.id.negative}";
        public static final String ERROR_CREATE_USER_REQUEST_EMPTY = "{error.userCreateRequest.user.null}";
        public static final String ERROR_UPDATE_USER_REQUEST_EMPTY = "{error.userUpdateRequest.user.null}";
        public static final String EXCEPTION_USER_NOT_FOUND = "exception.message.UserNotFoundException";
        public static final String EXCEPTION_INVALID_METHOD_ARGUMENT =
                "exception.message.MethodArgumentNotValidException";
        public static final String EXCEPTION_UNEXPECTED = "exception.message.UnexpectedException";


        private Message() {
        }
    }
}
