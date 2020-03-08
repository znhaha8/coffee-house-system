package com.wyz.coffee.service;

import com.wyz.coffee.dto.UserRoleDto;
import com.wyz.coffee.entity.User;

/**
 * @Description
 * @Author Jozo
 * @Date 2020/2/15 22:49
 **/
public interface SuperAdminService {
    int updateUser(User user);
    int updateUserRole(UserRoleDto userRoleDto);
}
