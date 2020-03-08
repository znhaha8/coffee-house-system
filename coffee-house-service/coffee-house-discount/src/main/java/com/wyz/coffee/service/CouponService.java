package com.wyz.coffee.service;

import com.wyz.coffee.dto.CouponDto;
import com.wyz.coffee.entity.Coupon;
import com.wyz.coffee.entity.UserCoupon;
import com.wyz.coffee.http.bean.dto.Pagination;

public interface CouponService {
    Pagination<Coupon> getCouponPageByName(int pageNum, int pageSize, String name);
    Pagination<CouponDto> getUserCouponPageByUsername(int pageNum, int pageSize, String name);
    int insert(Coupon coupon);
    int update(Coupon coupon);
    int delete(Integer id);
    int gift(UserCoupon userCoupon);

    int setUsed(Integer couponId);

    Coupon getById(Integer couponId);
}
