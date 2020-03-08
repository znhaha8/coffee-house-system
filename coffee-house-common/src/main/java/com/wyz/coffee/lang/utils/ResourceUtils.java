package com.wyz.coffee.lang.utils;

import com.wyz.coffee.lang.exception.FileIOException;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.util.FileCopyUtils;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;

/**
 * 通过相对路径读取文件,策略是先从项目路径拿,拿不到从classpath拿
 */
public class ResourceUtils {

    public static Resource getResource(String relativePath) {
        Resource resource = new FileSystemResource(relativePath);
        return resource.exists() ? resource : new ClassPathResource(relativePath);
    }

    public static InputStream getInputStream(String relativePath) {
        try {
            return getResource(relativePath).getInputStream();
        } catch (IOException e) {
            throw new FileIOException(e);
        }
    }

    public static String getString(String relativePath, Charset charset) {
        try {
            return new String(FileCopyUtils.copyToByteArray(getInputStream(relativePath)), charset);
        } catch (IOException e) {
            throw new FileIOException(e);
        }
    }

    public static String getString(String relativePath) {
        return getString(relativePath, Charset.forName("UTF-8"));
    }

    public static byte[] getByteArray(String relativePath) {
        try {
            return FileCopyUtils.copyToByteArray(getInputStream(relativePath));
        } catch (IOException e) {
            throw new FileIOException(e);
        }
    }
}
