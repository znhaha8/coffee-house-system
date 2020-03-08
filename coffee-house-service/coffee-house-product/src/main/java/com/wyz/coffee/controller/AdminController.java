package com.wyz.coffee.controller;

import com.wyz.coffee.entity.Product;
import com.wyz.coffee.entity.ProductSort;
import com.wyz.coffee.http.bean.dto.Pagination;
import com.wyz.coffee.http.bean.dto.Response;
import com.wyz.coffee.http.constant.ResCode;
import com.wyz.coffee.http.exception.ResponseException;
import com.wyz.coffee.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

/**
 * @Description
 * @Author Jozo
 * @Date 2020/2/28 13:50
 **/
@RestController
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    @Lazy
    ProductService productService;

    @GetMapping("/getPage")
    public Response<Pagination<Product>> getPage(
            @RequestParam(value = "name", defaultValue = "") String name,
            @RequestParam(value = "pageNum", defaultValue = "1") int pageNum,
            @RequestParam(value = "pageSize", defaultValue = "20") int pageSize) {
        return Response.success(productService.getPage(pageNum, pageSize, name));
    }

    @GetMapping("/getSortPage")
    public Response<Pagination<ProductSort>> getSortPage(
            @RequestParam(value = "name", defaultValue = "") String name,
            @RequestParam(value = "pageNum", defaultValue = "1") int pageNum,
            @RequestParam(value = "pageSize", defaultValue = "20") int pageSize) {
        return Response.success(productService.getSort(pageNum, pageSize, name));
    }

    @PostMapping("/insert")
    public Response insert(@RequestBody Product product) {
        productService.insert(product);
        return Response.success();
    }

    @PutMapping("/update")
    public Response update(@RequestBody Product product) {
        productService.update(product);
        return Response.success();
    }

    @DeleteMapping("/delete")
    public Response delete(@RequestParam("id") Integer id) {
        productService.delete(id);
        return Response.success();
    }

    @PostMapping("/sort/insert")
    public Response insertSort(@RequestBody ProductSort productSort) {
        productService.insertSort(productSort);
        return Response.success();
    }

    @PutMapping("/sort/update")
    public Response updateSort(@RequestBody ProductSort productSort) {
        productService.updateSort(productSort);
        return Response.success();
    }

    @DeleteMapping("/sort/delete")
    public Response deleteSort(@RequestParam("id") Integer id) {
        productService.deleteSort(id);
        return Response.success();
    }



    private String uploadPath = "E:\\img\\";

    @PostMapping(value = "/uploads")
    public Response uploads(@RequestParam("file") MultipartFile file) {
        String filename = "";
        try {
            //上传目录地址
            String uploadDir = uploadPath;

            //如果目录不存在，自动创建文件夹
            File dir = new File(uploadDir);

            if (!dir.exists()) {
                dir.mkdir();
            }

            //调用上传方法
            filename = executeUpload(uploadDir, file);

        } catch (Exception e) {
            //打印错误堆栈信息
            e.printStackTrace();
            throw new ResponseException(ResCode.R64003.build());
        }
        return Response.success(filename);
    }

    private String executeUpload(String uploadDir, MultipartFile file) throws Exception {
        //文件后缀名
        String suffix = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));
        //上传文件名
        String filename = UUID.randomUUID() + suffix;
        //服务器端保存的文件对象
        File serverFile = new File(uploadDir + filename);

        if (!serverFile.exists()) {
            //先得到文件的上级目录，并创建上级目录，在创建文件
            serverFile.getParentFile().mkdir();
            try {
                //创建文件
                serverFile.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        //将上传的文件写入到服务器端文件内
        file.transferTo(serverFile);

        return filename;
    }
}
