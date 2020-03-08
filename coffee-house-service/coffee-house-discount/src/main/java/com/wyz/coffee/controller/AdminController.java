package com.wyz.coffee.controller;

import com.wyz.coffee.entity.Coupon;
import com.wyz.coffee.entity.UserCoupon;
import com.wyz.coffee.http.bean.dto.Pagination;
import com.wyz.coffee.http.bean.dto.Response;
import com.wyz.coffee.service.CouponService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.web.bind.annotation.*;

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
    CouponService couponService;

    @GetMapping("/getPage")
    public Response<Pagination<Coupon>> getPage(
            @RequestParam(value = "name", defaultValue = "") String name,
            @RequestParam(value = "pageNum", defaultValue = "1") int pageNum,
            @RequestParam(value = "pageSize", defaultValue = "20") int pageSize) {
        return Response.success(couponService.getCouponPageByName(pageNum, pageSize, name));
    }

    @PostMapping("/insert")
    public Response insert(@RequestBody Coupon coupon) {
        couponService.insert(coupon);
        return Response.success();
    }

    @PutMapping("/update")
    public Response update(@RequestBody Coupon coupon) {
        couponService.update(coupon);
        return Response.success();
    }

    @DeleteMapping("/delete")
    public Response delete(@RequestParam("id") Integer id) {
        couponService.delete(id);
        return Response.success();
    }

    @PostMapping("/gift")
    public Response gift(@RequestBody UserCoupon userCoupon) {
        couponService.gift(userCoupon);
        return Response.success();
    }

}
