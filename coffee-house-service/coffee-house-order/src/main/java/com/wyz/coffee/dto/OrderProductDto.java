package com.wyz.coffee.dto;

import java.util.List;

/**
 * @Description
 * @Author Jozo
 * @Date 2020/3/5 21:25
 **/
public class OrderProductDto {
    private Integer id;
    private String orderId;
    List<String> products;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId == null ? null : orderId.trim();
    }

    public List<String> getProducts() {
        return products;
    }

    public void setProducts(List<String> products) {
        this.products = products;
    }
}
