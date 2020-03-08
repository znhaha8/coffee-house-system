package com.wyz.coffee.dao;

import com.wyz.coffee.dao.generated.OrderMapper;
import com.wyz.coffee.dto.SellsDto;
import com.wyz.coffee.entity.Order;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

/**
 * @Description
 * @Author Jozo
 * @Date 2020/2/28 20:02
 **/
@Repository
public interface OrderDao extends OrderMapper {
    List<Order> selectByUsernameAccurately(String username);
    List<Order> selectByUsername(String username);
    int setUnsuccessful(String id);
    int setPayed(Order order);
    SellsDto selectSellsAndTurnover(@Param("startTime")Date startTime, @Param("endTime")Date endTime);
}
