package com.wyz.coffee.config;

import org.apache.shiro.util.ByteSource;

import java.io.File;
import java.io.InputStream;
import java.io.Serializable;

/**
 * @Description
 * @Author Jozo
 * @Date 2020/2/28 11:03
 **/

public class MySimpleByteSource extends org.apache.shiro.util.SimpleByteSource
        implements Serializable {

    private static final long serialVersionUID = 5528101080905698238L;

    public MySimpleByteSource(byte[] bytes) {
        super(bytes);
    }

    public MySimpleByteSource(char[] chars) {
        super(chars);
    }

    public MySimpleByteSource(String string) {
        super(string);
    }

    public MySimpleByteSource(ByteSource source) {
        super(source);
    }

    public MySimpleByteSource(File file) {
        super(file);
    }

    public MySimpleByteSource(InputStream stream) {
        super(stream);
    }
}
