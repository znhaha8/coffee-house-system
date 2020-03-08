package com.wyz.coffee.entity;

import java.math.BigDecimal;

public class Product {
    private Integer id;

    private String name;

    private String description;

    private String picture;

    private BigDecimal price;

    private Integer sortId;

    private Boolean sellOut;

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

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture == null ? null : picture.trim();
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Integer getSortId() {
        return sortId;
    }

    public void setSortId(Integer sortId) {
        this.sortId = sortId;
    }

    public Boolean getSellOut() {
        return sellOut;
    }

    public void setSellOut(Boolean sellOut) {
        this.sellOut = sellOut;
    }
}