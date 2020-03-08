package com.wyz.coffee.dao.generated;

import com.wyz.coffee.entity.OrderCoupon;

public interface OrderCouponMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(OrderCoupon record);

    int insertSelective(OrderCoupon record);

    OrderCoupon selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(OrderCoupon record);

    int updateByPrimaryKey(OrderCoupon record);
}