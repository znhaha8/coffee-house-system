package com.wyz.coffee.dao;

import com.wyz.coffee.dao.generated.CouponMapper;
import com.wyz.coffee.entity.Coupon;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Description
 * @Author Jozo
 * @Date 2020/2/28 13:44
 **/
@Repository
public interface CouponDao extends CouponMapper {
    List<Coupon> selectByName(String name);
}
