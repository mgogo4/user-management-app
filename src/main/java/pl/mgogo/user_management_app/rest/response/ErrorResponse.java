/*
 * Copyright (c) 2019 the original author or authors.
 */

package pl.mgogo.user_management_app.rest.response;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 *
 * @author Michał Gogolewski
 */
public class ErrorResponse implements BaseResponse {
    private static final long serialVersionUID = -6451370128467209051L;

    private Integer status;
    private String error;
    @JsonFormat(pattern = "HH:mm:ss:SSS dd/MM/yyyy")
    private LocalDateTime timestamp = LocalDateTime.now();
    private List<String> messages = new ArrayList<>();

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public void addMessage(String message) {
        this.messages.add(message);
    }

    public void addMessages(List<String> messages) {
        this.messages.addAll(messages);
    }

    public List<String> getMessages() {
        return messages;
    }

    public void setMessages(List<String> messages) {
        this.messages = messages;
    }
}
