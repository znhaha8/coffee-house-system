package com.wyz.coffee.dto;

import java.util.List;

/**
 * @Description
 * @Author Jozo
 * @Date 2020/2/24 22:46
 **/
public class UserRoleDto {
    private Integer userId;
    private List<Integer> roleIds;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public List<Integer> getRoleIds() {
        return roleIds;
    }

    public void setRoleIds(List<Integer> roleIds) {
        this.roleIds = roleIds;
    }
}
