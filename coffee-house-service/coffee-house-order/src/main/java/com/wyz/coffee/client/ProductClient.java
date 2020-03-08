package com.wyz.coffee.client;

import com.wyz.coffee.entity.Product;
import com.wyz.coffee.http.bean.dto.Pagination;
import com.wyz.coffee.http.bean.dto.Response;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @Description
 * @Author Jozo
 * @Date 2020/3/5 21:35
 **/
@FeignClient(value = "coffee-product")
public interface ProductClient {
    @GetMapping("/product/server/getProductById")
    Response<Product> getProductById(@RequestParam Integer id);
    @GetMapping("product/admin/getPage")
    Response<Pagination<Product>> getPage(
            @RequestParam(value = "name", defaultValue = "") String name,
            @RequestParam(value = "pageNum",defaultValue = "1")int pageNum,
            @RequestParam(value = "pageSize",defaultValue = "20")int pageSize);
}
