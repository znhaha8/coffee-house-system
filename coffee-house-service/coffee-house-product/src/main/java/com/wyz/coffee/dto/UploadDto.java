package com.wyz.coffee.dto;

import org.springframework.web.multipart.MultipartFile;

/**
 * @Description
 * @Author Jozo
 * @Date 2020/3/6 23:41
 **/
public class UploadDto {
    private MultipartFile file;
    private String temp;

    public String getTemp() {
        return temp;
    }

    public void setTemp(String temp) {
        this.temp = temp == null ? null : temp.trim();
    }

    public MultipartFile getFile() {
        return file;
    }

    public void setFile(MultipartFile file) {
        this.file = file;
    }
}
