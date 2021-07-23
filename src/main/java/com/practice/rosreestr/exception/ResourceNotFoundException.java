package com.practice.rosreestr.exception;

public class ResourceNotFoundException extends ClientException {

    public ResourceNotFoundException() {
        super();
    }

    public ResourceNotFoundException(String message) {
        super(message);
    }

    public ResourceNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    protected ResourceNotFoundException(Throwable cause) {
        super(cause);
    }

}
