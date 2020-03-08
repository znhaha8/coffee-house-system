package com.wyz.coffee.client;

import com.wyz.coffee.entity.Coupon;
import com.wyz.coffee.http.bean.dto.Response;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @Description
 * @Author Jozo
 * @Date 2020/3/1 15:15
 **/
@FeignClient(value = "coffee-discount")
public interface DiscountClient {
    @PutMapping("/discount/user/useCoupon")
    Response useCoupon(@RequestParam("couponId") Integer couponId);

    @GetMapping("/discount/server/getCouponById")
    Response<Coupon> getCouponById(@RequestParam("id") Integer id);
}
