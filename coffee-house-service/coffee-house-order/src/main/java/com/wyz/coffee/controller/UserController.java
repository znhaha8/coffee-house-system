package com.wyz.coffee.controller;

import com.wyz.coffee.dto.OrderDto;
import com.wyz.coffee.http.bean.dto.Pagination;
import com.wyz.coffee.http.bean.dto.Response;
import com.wyz.coffee.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.web.bind.annotation.*;

/**
 * @Description
 * @Author Jozo
 * @Date 2020/2/28 13:47
 **/
@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    @Lazy
    OrderService orderService;

    @PostMapping("/insert")
    public Response<String> insert(@RequestBody OrderDto orderDto){
        String orderId = orderService.insert(orderDto);
        return Response.success(orderId);
    }

    @GetMapping("/getOrderPage")
    public Response<Pagination<OrderDto>> getOrderPage(
            @RequestParam(value = "username", defaultValue = "") String username,
            @RequestParam(value = "pageNum",defaultValue = "1")int pageNum,
            @RequestParam(value = "pageSize",defaultValue = "20")int pageSize){
        return Response.success(orderService.getOrderPageAccurately(username, pageNum, pageSize));
    }

    @PutMapping("/cancel")
    public Response cancel(@RequestBody OrderDto orderDto){
        orderService.cancel(orderDto.getId());
        return Response.success();
    }

    @PutMapping("/pay")
    public Response pay(@RequestBody OrderDto orderDto){
        orderService.pay(orderDto);
        return Response.success();
    }







}
