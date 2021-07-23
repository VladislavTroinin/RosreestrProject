package com.practice.rosreestr.exception;

public class WebServiceNotAccessibleException extends WebException {

    public WebServiceNotAccessibleException() {
        super();
    }

    public WebServiceNotAccessibleException(String message) {
        super(message);
    }

    public WebServiceNotAccessibleException(String message, Throwable cause) {
        super(message, cause);
    }

    protected WebServiceNotAccessibleException(Throwable cause) {
        super(cause);
    }

}
