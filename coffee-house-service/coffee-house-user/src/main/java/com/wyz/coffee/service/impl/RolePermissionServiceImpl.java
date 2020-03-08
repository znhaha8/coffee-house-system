package com.wyz.coffee.service.impl;

import com.wyz.coffee.dao.RolePermissionDao;
import com.wyz.coffee.entity.RolePermission;
import com.wyz.coffee.service.RolePermissionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * @Description
 * @Author Jozo
 * @Date 2020/2/14 20:10
 **/
@Service
@Slf4j
public class RolePermissionServiceImpl implements RolePermissionService {
    @Autowired
    @Lazy
    RolePermissionDao rolePermissionDao;

    @Override
    public int insert(RolePermission rolePermission) {
        rolePermission.setCreateTime(new Date(System.currentTimeMillis()));
        return rolePermissionDao.insert(rolePermission);
    }

    @Override
    public int update(RolePermission rolePermission) {
        return rolePermissionDao.updateByPrimaryKeySelective(rolePermission);
    }

    @Override
    public int deleteByRoleIdAndPermissionId(RolePermission rolePermission) {
        return rolePermissionDao.deleteByRoleIdAndPermissionId(rolePermission.getRoleId(), rolePermission.getPermissionId());
    }
}
