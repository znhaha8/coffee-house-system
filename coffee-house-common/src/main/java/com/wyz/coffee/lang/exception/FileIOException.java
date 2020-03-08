package com.wyz.coffee.lang.exception;

/**
 * 文件的读写操作异常
 */
public class FileIOException extends RuntimeException {
    public FileIOException() {
        super();
    }

    public FileIOException(String message) {
        super(message);
    }

    public FileIOException(String message, Throwable cause) {
        super(message, cause);
    }

    public FileIOException(Throwable cause) {
        super(cause);
    }

    public FileIOException(Exception e) {
        super(e.getMessage(), e);
    }
}
