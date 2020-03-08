package com.wyz.coffee.controller;

import com.wyz.coffee.entity.Product;
import com.wyz.coffee.http.bean.dto.Response;
import com.wyz.coffee.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description
 * @Author Jozo
 * @Date 2020/3/5 21:38
 **/
@RequestMapping("/server")
@RestController
public class ServerController {
    @Autowired
    @Lazy
    private ProductService productService;

    @GetMapping("/getProductById")
    public Response<Product> getProductById(@RequestParam Integer id){
        return Response.success(productService.getProductById(id));
    }
}
