package com.wyz.coffee.dto;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @Description
 * @Author Jozo
 * @Date 2020/2/29 0:33
 **/
public class CouponDto {
    private Integer id;

    private String name;

    private String description;

    private BigDecimal value;

    private Date getTime;

    private Date destroyTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }

    public BigDecimal getValue() {
        return value;
    }

    public void setValue(BigDecimal value) {
        this.value = value;
    }

    public Date getGetTime() {
        return getTime;
    }

    public void setGetTime(Date getTime) {
        this.getTime = getTime;
    }

    public Date getDestroyTime() {
        return destroyTime;
    }

    public void setDestroyTime(Date destroyTime) {
        this.destroyTime = destroyTime;
    }
}
