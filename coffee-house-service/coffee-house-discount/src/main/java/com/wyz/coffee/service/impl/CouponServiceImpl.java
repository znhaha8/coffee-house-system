package com.wyz.coffee.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.wyz.coffee.dao.CouponDao;
import com.wyz.coffee.dao.UserCouponDao;
import com.wyz.coffee.dto.CouponDto;
import com.wyz.coffee.entity.Coupon;
import com.wyz.coffee.entity.UserCoupon;
import com.wyz.coffee.http.bean.dto.Pagination;
import com.wyz.coffee.service.CouponService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Description
 * @Author Jozo
 * @Date 2020/2/28 13:54
 **/
@Service
public class CouponServiceImpl implements CouponService {
    @Autowired
    @Lazy
    CouponDao couponDao;
    @Autowired
    @Lazy
    UserCouponDao userCouponDao;

    @Override
    public Pagination<Coupon> getCouponPageByName(int pageNum, int pageSize, String name) {
        Page<Coupon> coupons = PageHelper.startPage(pageNum, pageSize).doSelectPage(() -> couponDao.selectByName(name));
        Pagination<Coupon> res = new Pagination<>();
        BeanUtils.copyProperties(coupons, res);
        return res;
    }

    @Override
    public Pagination<CouponDto> getUserCouponPageByUsername(int pageNum, int pageSize, String username) {
        Page<UserCoupon> userCoupons = PageHelper.startPage(pageNum, pageSize).doSelectPage(() -> userCouponDao.selectByUsername(username));
        List<CouponDto> couponDtos = userCoupons.stream().map(userCoupon -> {
            Date now = new Date(System.currentTimeMillis());
            if(userCoupon.getDestroyTime().before(now)) return null;
            Coupon coupon = couponDao.selectByPrimaryKey(userCoupon.getCouponId());
            CouponDto couponDto = new CouponDto();
            BeanUtils.copyProperties(coupon, couponDto);
            couponDto.setGetTime(userCoupon.getGetTime());
            couponDto.setDestroyTime(userCoupon.getDestroyTime());
            return couponDto;
        }).collect(Collectors.toList());
        Pagination<CouponDto> res = new Pagination<>();
        BeanUtils.copyProperties(userCoupons, res, "result");
        res.setResult(couponDtos);
        return res;
    }



    @Override
    public int insert(Coupon coupon) {
        coupon.setCreateTime(new Date(System.currentTimeMillis()));
        return couponDao.insert(coupon);
    }

    @Override
    public int update(Coupon coupon) {
        return couponDao.updateByPrimaryKeySelective(coupon);
    }

    @Override
    public int delete(Integer id) {
        return couponDao.deleteByPrimaryKey(id);
    }

    @Override
    public int gift(UserCoupon userCoupon) {
        userCoupon.setUsed(false);
        userCouponDao.insert(userCoupon);
        return 0;
    }
    @Override
    public int setUsed(Integer couponId){
        return userCouponDao.setUsed(couponId);
    }

    @Override
    public Coupon getById(Integer couponId){
        return couponDao.selectByPrimaryKey(couponId);
    }
}
