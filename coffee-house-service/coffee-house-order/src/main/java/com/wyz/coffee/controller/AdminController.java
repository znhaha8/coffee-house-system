package com.wyz.coffee.controller;

import com.wyz.coffee.dto.ProductSellsDto;
import com.wyz.coffee.dto.SellsDto;
import com.wyz.coffee.http.bean.dto.Pagination;
import com.wyz.coffee.http.bean.dto.Response;
import com.wyz.coffee.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @Description
 * @Author Jozo
 * @Date 2020/2/28 13:50
 **/
@RestController
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    @Lazy
    private OrderService orderService;

    @GetMapping("/getSellsAndTurnover")
    public Response<SellsDto> getSellsAndTurnover(@RequestParam("startTime")String startTime,
                                                  @RequestParam("endTime") String endTime) throws ParseException {

        startTime = startTime.replace("Z", " UTC");//是空格+UTC
        endTime = endTime.replace("Z", " UTC");//是空格+UTC
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS Z");//格式化的表达式
        Date s1 = format.parse(startTime);
        Date s2 = format.parse(endTime);
        SellsDto sellsAndTurnover = orderService.getSellsAndTurnover(s1, s2);
        return Response.success(sellsAndTurnover);
    }

    @GetMapping("/getProductSellsPage")
    public Response<Pagination<ProductSellsDto>> getProductSells(@RequestParam(value = "pageNum", defaultValue = "1") int pageNum,
                                              @RequestParam(value = "pageSize", defaultValue = "20") int pageSize,
                                              @RequestParam("startTime") String startTime,
                                              @RequestParam("endTime") String endTime) throws ParseException {
        startTime = startTime.replace("Z", " UTC");//是空格+UTC
        endTime = endTime.replace("Z", " UTC");//是空格+UTC
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS Z");//格式化的表达式
        Date s1 = format.parse(startTime);
        Date s2 = format.parse(endTime);
        SellsDto sellsAndTurnover = orderService.getSellsAndTurnover(s1, s2);
        Pagination<ProductSellsDto> res = orderService.getProductSellsByProductId(pageNum, pageSize, s1, s2);
        return Response.success(res);
    }
}
