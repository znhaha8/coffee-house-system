package com.wyz.coffee.lang.exception;

public class XmlException extends RuntimeException {
    public XmlException() {
        super();
    }

    public XmlException(String message) {
        super(message);
    }

    public XmlException(String message, Throwable cause) {
        super(message, cause);
    }

    public XmlException(Throwable cause) {
        super(cause);
    }

    public XmlException(Exception e) {
        super(e.getMessage(), e);
    }
}
