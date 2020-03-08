package com.wyz.coffee.service.impl;

import com.wyz.coffee.dao.PermissionDao;
import com.wyz.coffee.entity.Permission;
import com.wyz.coffee.service.PermissionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Set;

/**
 * @Description
 * @Author Jozo
 * @Date 2020/2/17 20:17
 **/
@Service
@Slf4j
public class PermissionServiceImpl implements PermissionService {
    @Autowired
    @Lazy
    private PermissionDao permissionDao;
    @Override
    public int createPermission(Permission permission) {
        permission.setCreateTime(new Date(System.currentTimeMillis()));
        return permissionDao.insert(permission);
    }

    @Override
    public List<Permission> getPermissionByName(String name) {
        return permissionDao.selectByName(name);
    }

    @Override
    public int updatePermissionById(Permission permission) {
        return permissionDao.updateByPrimaryKeySelective(permission);
    }

    @Override
    public int deletePermissionById(Integer id) {
        return permissionDao.deleteByPrimaryKey(id);
    }

    @Override
    public List<Permission> findPermissionsByRoleId(Integer roleId) {
        return permissionDao.selectByRoleId(roleId);
    }

    @Override
    public Set<String> selectPermissionStringSetByUsername(String username) {
        return permissionDao.selectPermissionStringSetByUsername(username);
    }
}
