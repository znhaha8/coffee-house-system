package com.wyz.coffee.controller;

import com.wyz.coffee.dto.CouponDto;
import com.wyz.coffee.http.bean.dto.Pagination;
import com.wyz.coffee.http.bean.dto.Response;
import com.wyz.coffee.service.CouponService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.web.bind.annotation.*;

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
    CouponService couponService;

    @GetMapping("/getPage")
    public Response<Pagination<CouponDto>> getPage(
            @RequestParam(value = "username", defaultValue = "") String username,
            @RequestParam(value = "pageNum", defaultValue = "1") int pageNum,
            @RequestParam(value = "pageSize", defaultValue = "20") int pageSize) {
        Pagination<CouponDto> page = couponService.getUserCouponPageByUsername(pageNum, pageSize, username);
        return Response.success(page);
    }

    @PutMapping("/useCoupon")
    public Response useCoupon(@RequestParam("couponId") Integer couponId){
        couponService.setUsed(couponId);
        return Response.success();
    }

}

