package com.wyz.coffee.config;

import org.apache.shiro.util.ByteSource;

/**
 * @Description
 * @Author Jozo
 * @Date 2020/2/28 11:04
 **/
public class MyByteSourceUtils {
    public static ByteSource bytes(byte[] bytes){
        return new MySimpleByteSource(bytes);
    }
    public static ByteSource bytes(String arg0){
        return new MySimpleByteSource(arg0.getBytes());
    }
}
