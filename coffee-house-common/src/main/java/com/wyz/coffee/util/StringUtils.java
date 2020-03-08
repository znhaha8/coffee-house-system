package com.wyz.coffee.util;

/**
 * @Description 字符串相关方法
 * @Author Jozo
 * @Date 2020/2/17 23:47
 **/
public class StringUtils {

    /**
     * 是否为空
     * @param str 字符串
     * @return true 空 false 非空
     */
    public static Boolean isEmpty(String str) {
        if(str == null || str.equals("")) {
            return true;
        }

        return false;
    }
}
