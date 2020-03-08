package com.wyz.coffee.http.exception;


import com.wyz.coffee.http.bean.dto.Response;

/**
 * Created by liyu on 2018/1/25.
 * 定义为直接将错误信息返回的异常
 * 建议业务层直接使用此异常作为业务异常
 */
public class ResponseException extends RuntimeException {
    private Response response;

    public ResponseException() {
    }

    public ResponseException(String code, String msg) {
        this(new Response(code, msg), null);
    }

    public ResponseException(String code, String msg, Throwable cause) {
        this(new Response(code, msg), cause);
    }

    public <T> ResponseException(String code, String msg, T data) {
        this(new Response(code, msg, data), null);
    }

    public <T> ResponseException(String code, String msg, T data, Throwable cause) {
        this(new Response(code, msg, data), cause);
    }

    public ResponseException(Response response) {
        this(response, null);
    }

    public ResponseException(Response response, Throwable cause) {
        super(response.getMsg(), cause);
        this.response = response;
    }

    public Response getResponse() {
        return response;
    }
}