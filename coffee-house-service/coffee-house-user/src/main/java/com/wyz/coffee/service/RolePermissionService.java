package com.wyz.coffee.service;

import com.wyz.coffee.entity.RolePermission;

/**
 * @Description
 * @Author Jozo
 * @Date 2020/2/14 20:05
 **/
public interface RolePermissionService {
    int insert(RolePermission rolePermission);
    int update(RolePermission rolePermission);
    int deleteByRoleIdAndPermissionId(RolePermission rolePermission);
}
