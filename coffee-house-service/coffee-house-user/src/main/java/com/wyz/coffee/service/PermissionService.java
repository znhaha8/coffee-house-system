package com.wyz.coffee.service;

import com.wyz.coffee.entity.Permission;

import java.util.List;
import java.util.Set;

/**
 * @Description
 * @Author Jozo
 * @Date 2020/2/12 17:05
 **/
public interface PermissionService {
    int createPermission(Permission permission);
    List<Permission> getPermissionByName(String name);
    int updatePermissionById(Permission permission);
    int deletePermissionById(Integer id);
    List<Permission> findPermissionsByRoleId(Integer roleId);
    Set<String> selectPermissionStringSetByUsername(String username);


}
