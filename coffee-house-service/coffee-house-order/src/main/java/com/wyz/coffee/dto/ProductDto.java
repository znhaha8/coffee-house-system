package com.wyz.coffee.dto;

import com.wyz.coffee.entity.Product;

/**
 * @Description
 * @Author Jozo
 * @Date 2020/2/28 19:03
 **/
public class ProductDto extends Product {
    private Integer amount;

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }
}
