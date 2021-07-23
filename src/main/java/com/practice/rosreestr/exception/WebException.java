package com.practice.rosreestr.exception;

public class WebException extends Exception {

    public WebException() {
        super();
    }

    public WebException(String message) {
        super(message);
    }

    public WebException(String message, Throwable cause) {
        super(message, cause);
    }

    protected WebException(Throwable cause) {
        super(cause);
    }

}
