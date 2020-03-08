package com.wyz.coffee.service;

import com.wyz.coffee.dto.OrderDto;
import com.wyz.coffee.dto.ProductSellsDto;
import com.wyz.coffee.dto.SellsDto;
import com.wyz.coffee.http.bean.dto.Pagination;

import java.util.Date;

/**
 * @Description
 * @Author Jozo
 * @Date 2020/2/28 18:25
 **/
public interface OrderService {
    Pagination<OrderDto> getOrderPageAccurately(String username, int pageNum, int pageSize);
    String insert(OrderDto orderDto);
    int cancel(String id);
    int pay(OrderDto orderDto);
    int delete(Integer id);

    Pagination<ProductSellsDto> getProductSellsByProductId(int pageNum, int pageSize, Date startTIme, Date endTime);

    SellsDto getSellsAndTurnover(Date startTIme, Date endTime);
}
