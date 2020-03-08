package com.wyz.coffee.dao;

import com.wyz.coffee.dao.generated.UserCouponMapper;
import com.wyz.coffee.entity.UserCoupon;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Description
 * @Author Jozo
 * @Date 2020/2/28 20:33
 **/
@Repository
public interface UserCouponDao extends UserCouponMapper {
    int setUsed(Integer couponId);
    List<UserCoupon> selectByUsername(String username);
}
