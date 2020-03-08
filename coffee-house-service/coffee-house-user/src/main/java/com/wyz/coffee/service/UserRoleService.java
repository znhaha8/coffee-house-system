package com.wyz.coffee.service;

import com.wyz.coffee.entity.UserRole;

/**
 * @Description
 * @Author Jozo
 * @Date 2020/2/14 20:05
 **/
public interface UserRoleService {
    int insert(UserRole userRole);
    int updateById(UserRole userRole);
}
