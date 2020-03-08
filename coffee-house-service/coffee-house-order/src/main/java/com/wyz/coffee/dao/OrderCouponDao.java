package com.wyz.coffee.dao;

import com.wyz.coffee.dao.generated.OrderCouponMapper;
import com.wyz.coffee.entity.OrderCoupon;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderCouponDao extends OrderCouponMapper {
    List<OrderCoupon> selectByOrderId(String orderId);
}
