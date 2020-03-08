package com.wyz.coffee.dto;

/**
 * @Description
 * @Author Jozo
 * @Date 2020/3/5 23:28
 **/
public class ProductSellsDto {
    private Integer id;
    private String name;
    private Integer sells;

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

    public Integer getSells() {
        return sells;
    }

    public void setSells(Integer sells) {
        this.sells = sells;
    }
}
