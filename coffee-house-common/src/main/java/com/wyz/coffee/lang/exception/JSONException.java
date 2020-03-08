package com.wyz.coffee.lang.exception;

public class JSONException extends RuntimeException {
    public JSONException() {
        super();
    }

    public JSONException(String message) {
        super(message);
    }

    public JSONException(String message, Throwable cause) {
        super(message, cause);
    }

    public JSONException(Throwable cause) {
        super(cause);
    }

    public JSONException(Exception e) {
        super(e.getMessage(), e);
    }
}
