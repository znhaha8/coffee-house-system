package com.wyz.coffee.controller;

import com.wyz.coffee.entity.Coupon;
import com.wyz.coffee.http.bean.dto.Response;
import com.wyz.coffee.service.CouponService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description
 * @Author Jozo
 * @Date 2020/3/5 21:57
 **/
@RequestMapping("/server")
@RestController
public class ServerController {
    @Autowired
    @Lazy
    private CouponService couponService;

    @GetMapping("/getCouponById")
    public Response<Coupon> getCouponById(@RequestParam("id") Integer id){
        return Response.success(couponService.getById(id));
    }
}
