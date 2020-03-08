package com.wyz.coffee.dto;

import com.wyz.coffee.entity.Role;
import com.wyz.coffee.entity.User;

import java.util.List;

/**
 * @Description
 * @Author Jozo
 * @Date 2020/2/15 13:01
 **/
public class UserDto extends User {
    private List<Role> roles;

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

}
