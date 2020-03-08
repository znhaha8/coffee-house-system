package com.wyz.coffee.controller.advice;


import com.wyz.coffee.http.bean.dto.Response;
import com.wyz.coffee.http.constant.ResCode;
import com.wyz.coffee.http.exception.ResponseException;
import com.wyz.coffee.lang.exception.DatabaseException;
import com.wyz.coffee.lang.exception.FileIOException;
import com.wyz.coffee.lang.exception.JSONException;
import io.lettuce.core.RedisException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.transaction.TransactionException;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.multipart.support.MissingServletRequestPartException;

import javax.validation.ConstraintViolationException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.stream.Collectors;

/**
 * @Description 统一捕获异常的处理类
 * @Author Jozo
 * @Date 2020/2/18 17:39
 **/

@RestControllerAdvice
public class ExceptionControllerAdvice {
    private Log logger = LogFactory.getLog(ExceptionControllerAdvice.class);
    @ExceptionHandler(ResponseException.class)
    public Response response(ResponseException e) {
        return e.getResponse();
    }

//    @ExceptionHandler(UnauthorizedException.class)
//    public Response response(UnauthorizedException e) {
//        ResCode resCode = ResCode.R606;
//        return new Response(resCode.code(),  resCode.msg()+ " : " + e.getMessage());
//    }

    @ExceptionHandler({HttpRequestMethodNotSupportedException.class, HttpMediaTypeNotSupportedException.class})
    public Response httpRequestMethodNotSupportedException(Exception e) {
        ResCode resCode = ResCode.R64005;
        return new Response(resCode.code(), resCode.msg());
    }

    @ExceptionHandler(BindException.class)
    public Response valid(BindException e) {
        String msg = e.getBindingResult().getAllErrors().stream()
                .map(error -> (error instanceof FieldError ? (((FieldError) error).getField()) : error.getDefaultMessage())
                        + ":" + error.getDefaultMessage()).collect(Collectors.joining(","));
        ResCode resCode = ResCode.R64009;
        return new Response(resCode.code(), msg);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Response valid(MethodArgumentNotValidException e) {
        String msg = e.getBindingResult().getAllErrors().stream()
                .map(error -> (error instanceof FieldError ? (((FieldError) error).getField()) : error.getDefaultMessage())
                        + ":" + error.getDefaultMessage()).collect(Collectors.joining(","));
        ResCode resCode = ResCode.R64009;
        return new Response(resCode.code(), msg);
    }

    @ExceptionHandler({
            MissingServletRequestPartException.class,
            HttpMessageNotReadableException.class,
            MissingServletRequestParameterException.class,
            ConstraintViolationException.class,
            MethodArgumentTypeMismatchException.class,
            ServletRequestBindingException.class
    })
    public Response databind(Exception e) {
        logger.error(e.getMessage(), e);
        ResCode resCode = ResCode.R64009;
        return new Response(resCode.code(), resCode.msg());
    }

    @ExceptionHandler({DatabaseException.class, SQLException.class, TransactionException.class})
    public Response database(Exception e) {
        logger.error(e.getMessage(), e);
        ResCode resCode = ResCode.R64001;
        return new Response(resCode.code(), resCode.msg());
    }

    @ExceptionHandler(HttpClientErrorException.class)
    public Response http(HttpClientErrorException e) {
        logger.error(e.getMessage(), e);
        ResCode resCode = ResCode.R64005;
        return new Response(resCode.code(), resCode.msg());
    }

    @ExceptionHandler(RedisException.class)
    public Response redis(RedisException e) {
        logger.error(e.getMessage(), e);
        ResCode resCode = ResCode.R64002;
        return new Response(resCode.code(), resCode.msg());
    }

    @ExceptionHandler({FileIOException.class, IOException.class})
    public Response io(Exception e) {
        logger.error(e.getMessage(), e);
        ResCode resCode = ResCode.R64003;
        return new Response(resCode.code(), resCode.msg());
    }

    @ExceptionHandler({JSONException.class, com.alibaba.fastjson.JSONException.class})
    public Response json(Exception e) {
        logger.error(e.getMessage(), e);
        ResCode resCode = ResCode.R64007;
        return new Response(resCode.code(), resCode.msg());
    }


    @ExceptionHandler(Exception.class)
    public Response exception(Exception e) {
        logger.error(e.getMessage(), e);
        ResCode resCode = ResCode.R64000;
        return new Response(resCode.code(), resCode.msg());
    }
}

