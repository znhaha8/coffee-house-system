
package com.wyz.coffee.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.wyz.coffee.client.DiscountClient;
import com.wyz.coffee.client.ProductClient;
import com.wyz.coffee.constants.PayConst;
import com.wyz.coffee.dao.OrderCouponDao;
import com.wyz.coffee.dao.OrderDao;
import com.wyz.coffee.dao.OrderProductDao;
import com.wyz.coffee.dto.OrderDto;
import com.wyz.coffee.dto.ProductDto;
import com.wyz.coffee.dto.ProductSellsDto;
import com.wyz.coffee.dto.SellsDto;
import com.wyz.coffee.entity.*;
import com.wyz.coffee.http.bean.dto.Pagination;
import com.wyz.coffee.service.OrderService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;


/**
 * @Description
 * @Author Jozo
 * @Date 2020/2/28 18:31
 **/
@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    @Lazy
    private OrderDao orderDao;
    @Autowired
    @Lazy
    private OrderProductDao orderProductDao;
    @Autowired
    @Lazy
    private OrderCouponDao orderCouponDao;
    @Autowired
    @Lazy
    private DiscountClient discountClient;
    @Autowired
    @Lazy
    private ProductClient productClient;


    @Override
    public Pagination<OrderDto> getOrderPageAccurately(String username, int pageNum, int pageSize) {
        Page<Order> page = PageHelper.startPage(pageNum, pageSize).doSelectPage(() -> orderDao.selectByUsernameAccurately(username));
        Pagination<OrderDto> res = new Pagination<>();
        List<OrderDto> orderDtos = page.stream().map(order -> {
            List<OrderProduct> orderProducts = orderProductDao.selectByOrderId(order.getId());
            List<ProductDto> productDtos = orderProducts.stream().map(orderProduct -> {
                Product product = productClient.getProductById(orderProduct.getProductId()).getData();
                ProductDto productDto = new ProductDto();
                BeanUtils.copyProperties(product, productDto);
                productDto.setAmount(orderProduct.getAmount());
                return productDto;
            }).collect(Collectors.toList());
            List<OrderCoupon> orderCoupons = orderCouponDao.selectByOrderId(order.getId());
            List<Coupon> coupons = orderCoupons.stream().map(orderCoupon -> {
                return discountClient.getCouponById(orderCoupon.getCouponId()).getData();
            }).collect(Collectors.toList());
            OrderDto orderDto = new OrderDto();
            BeanUtils.copyProperties(order, orderDto);
            orderDto.setCoupons(coupons);
            orderDto.setProductDtos(productDtos);
            return orderDto;
        }).collect(Collectors.toList());
        BeanUtils.copyProperties(page, res, "result");
        res.setResult(orderDtos);
        return res;
    }


    @Override
    @Transactional
    public String insert(OrderDto orderDto) {
        Order order = new Order();
        List<ProductDto> productDtos = orderDto.getProductDtos();
        List<Coupon> coupons = orderDto.getCoupons();
        BeanUtils.copyProperties(orderDto, order);
        order.setSuccess(true);
        order.setCreateTime(new Date(System.currentTimeMillis()));
        order.setId(UUID.randomUUID().toString());
        orderDao.insert(order);
        productDtos.forEach(productDto -> {
            OrderProduct orderProduct = new OrderProduct();
            orderProduct.setOrderId(order.getId());
            orderProduct.setProductId(productDto.getId());
            orderProduct.setAmount(productDto.getAmount());
            orderProduct.setCreateTime(new Date(System.currentTimeMillis()));
            orderProductDao.insert(orderProduct);
        });
        if(coupons != null) {
            coupons.forEach(coupon -> {
                OrderCoupon orderCoupon = new OrderCoupon();
                orderCoupon.setOrderId(order.getId());
                orderCoupon.setCouponId(coupon.getId());
                orderCouponDao.insert(orderCoupon);
            });
        }
        return order.getId();
    }

    @Override
    @Transactional
    public int cancel(String id) {
        return orderDao.setUnsuccessful(id);
    }

    @Override
    public int pay(OrderDto orderDto) {
        List<Coupon> coupons = orderDto.getCoupons();
        Order order = new Order();
        order.setId(orderDto.getId());
        order.setPayWay(orderDto.getPayWay());
        //非现金模拟调用第三方线上支付
        if (!orderDto.getPayWay().equals(PayConst.PAY_CASH)) {
            order.setPayId(UUID.randomUUID().toString());
        }
        order.setPayTime(new Date(System.currentTimeMillis()));
        order.setSuccess(true);
        orderDao.updateByPrimaryKeySelective(order);
        if(coupons != null) {
            coupons.stream().forEach(coupon -> {
                discountClient.useCoupon(coupon.getId());
            });
        }
        return 1;
    }

    @Override
    @Transactional
    public int delete(Integer id) {
        return 0;
    }

    @Override
    public Pagination<ProductSellsDto> getProductSellsByProductId(int pageNum, int pageSize, Date startTIme, Date endTime){
        Pagination<Product> page = productClient.getPage(null, pageNum, pageSize).getData();
        List<ProductSellsDto> productSellsDtos = page.getResult().stream().map(product -> {
            ProductSellsDto productSellsDto = new ProductSellsDto();
            productSellsDto.setId(product.getId());
            productSellsDto.setName(product.getName());
            int sells = orderProductDao.selectProductSellsByProductId(product.getId(), startTIme, endTime);
            productSellsDto.setSells(sells);
            return productSellsDto;
        }).collect(Collectors.toList());
        Pagination<ProductSellsDto> res = new Pagination<>();
        BeanUtils.copyProperties(page, res, "result");
        res.setResult(productSellsDtos);
        return res;
    }

    @Override
    public SellsDto getSellsAndTurnover(Date startTIme, Date endTime){
        SellsDto sellsDto = orderDao.selectSellsAndTurnover(startTIme, endTime);
        return sellsDto;
    }
}

