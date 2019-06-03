/*
 * Copyright (c) 2019 the original author or authors.
 */

package pl.mgogo.user_management_app.rest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import pl.mgogo.user_management_app.rest.exception.UserNotFoundException;
import pl.mgogo.user_management_app.rest.response.ErrorResponse;
import pl.mgogo.user_management_app.util.Constants;

import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

/**
 * Created by IntelliJ IDEA.
 *
 * @author Michał Gogolewski
 */
@Order(Ordered.HIGHEST_PRECEDENCE)
@ControllerAdvice
public class ErrorHandler {
    private final MessageSource messageSource;

    @Autowired
    public ErrorHandler(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    @ExceptionHandler(value = Exception.class)
    public ResponseEntity<ErrorResponse> defaultErrorHandler(Exception e, Locale locale) {
        final ErrorResponse response = new ErrorResponse();

        final ResponseStatus responseStatus = AnnotationUtils.findAnnotation(e.getClass(), ResponseStatus.class);
        if (responseStatus != null) {
            final HttpStatus httpStatus = responseStatus.code();
            response.setError(httpStatus.getReasonPhrase());
            response.setStatus(httpStatus.value());
            response.addMessage(e.getLocalizedMessage());
        } else {
            response.setError(HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase());
            response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
            response.addMessage(messageSource.getMessage(Constants.Message.EXCEPTION_UNEXPECTED, null, locale));
        }
        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleUserNotFoundException(UserNotFoundException exception, Locale locale) {

        final ErrorResponse response = new ErrorResponse();
        response.setError(HttpStatus.NOT_FOUND.getReasonPhrase());
        response.setStatus(HttpStatus.NOT_FOUND.value());
        response.addMessage(messageSource.getMessage(exception.getMessage(), new Object[]{exception.getUserId()},
                locale));
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleArgumentNotValidException(MethodArgumentNotValidException exception,
                                                                         Locale locale) {
        final ErrorResponse response = new ErrorResponse();
        response.setError(HttpStatus.BAD_REQUEST.getReasonPhrase());
        response.setStatus(HttpStatus.BAD_REQUEST.value());
        response.addMessage(
                messageSource.getMessage(Constants.Message.EXCEPTION_INVALID_METHOD_ARGUMENT, null, locale));

        List<String> errorMessages = exception.getBindingResult()
                .getAllErrors()
                .stream()
                .map(DefaultMessageSourceResolvable::getDefaultMessage)
                .collect(Collectors.toList());

        response.addMessages(errorMessages);

        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }
}
