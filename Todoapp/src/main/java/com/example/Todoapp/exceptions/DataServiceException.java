package com.example.Todoapp.exceptions;

public class DataServiceException extends RuntimeException {
    public DataServiceException() {
        this(null);
    }

    public DataServiceException(String message) {
        super(message, null);
    }

    public DataServiceException(String message, Throwable cause) {
        super(message, cause);
    }

    @Override
    public String getMessage() {
        Throwable cause = this.getCause();
        return String.format("{message : %s%s}", super.getMessage(), cause != null ? ", causeMessage : " + cause.getMessage() : "");
    }
}
