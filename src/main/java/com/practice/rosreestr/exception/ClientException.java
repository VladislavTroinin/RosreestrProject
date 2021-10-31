package com.practice.rosreestr.exception;

public class ClientException extends RuntimeException {

    public ClientException() {
        super();
    }

    public ClientException(String message) {
        super(message);
    }

    public ClientException(String message, Throwable cause) {
        super(message, cause);
    }

    protected ClientException(Throwable cause) {
        super(cause);
    }

}
