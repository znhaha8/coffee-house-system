package com.wyz.coffee.http.bean.dto;


import com.wyz.coffee.http.exception.ResponseException;

public class Response<T> {
    private String code;
    private String msg;
    private T data;

    public Response<T> check() {
        if (!"100".equals(this.code)) {
            throw new ResponseException(this);
        }
        return this;
    }

    public Response() {
    }

    public Response(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public Response(String code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public static <T> Response<T> success() {
        return success(null);
    }

    public static <T> Response<T> success(T data) {
        return new Response<>("100", "成功", data);
    }
}
