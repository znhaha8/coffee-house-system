package com.wyz.coffee.controller;

import com.wyz.coffee.entity.Product;
import com.wyz.coffee.entity.ProductSort;
import com.wyz.coffee.http.bean.dto.Response;
import com.wyz.coffee.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Description
 * @Author Jozo
 * @Date 2020/2/28 13:47
 **/
@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    @Lazy
    ProductService productService;
    @GetMapping("/getProductBySortId")
    public Response<List<Product>> getProductBySortId(@RequestParam(value = "sortId") Integer sortId){
        List<Product> res = null;
        if (sortId == null || sortId == -1){
            res = productService.getByName("");
        }else{
            res = productService.getProductBySortId(sortId);
        }
        return Response.success(res);
    }

    @GetMapping("/getAllSort")
    public Response<List<ProductSort>> getAllSort(){
        List<ProductSort> res = productService.getAllSort();
        return Response.success(res);
    }

}
