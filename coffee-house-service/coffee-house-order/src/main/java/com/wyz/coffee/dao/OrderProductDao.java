package com.wyz.coffee.dao;

import com.wyz.coffee.dao.generated.OrderProductMapper;
import com.wyz.coffee.entity.OrderProduct;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

/**
 * @Description
 * @Author Jozo
 * @Date 2020/2/28 16:16
 **/
@Repository
public interface OrderProductDao extends OrderProductMapper {
    List<OrderProduct> selectByOrderId(String orderId);
    int setFinished(String orderId);
    int setUnfinished(String orderId);
    List<OrderProduct> selectUnfinished();
    Integer selectProductSellsByProductId(@Param("productId") Integer productId, @Param("startTime") Date startTime, @Param("endTime")Date endTime);
}
