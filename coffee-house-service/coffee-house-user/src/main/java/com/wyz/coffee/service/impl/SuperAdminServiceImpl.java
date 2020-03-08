package com.wyz.coffee.service.impl;

import com.wyz.coffee.constans.ResCode;
import com.wyz.coffee.dao.UserDao;
import com.wyz.coffee.dao.UserRoleDao;
import com.wyz.coffee.dto.UserRoleDto;
import com.wyz.coffee.entity.User;
import com.wyz.coffee.entity.UserRole;
import com.wyz.coffee.http.exception.ResponseException;
import com.wyz.coffee.service.SuperAdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * @Description
 * @Author Jozo
 * @Date 2020/2/15 22:49
 **/
@Service
public class SuperAdminServiceImpl implements SuperAdminService {
    @Autowired
    @Lazy
    UserDao userDao;
    @Autowired
    @Lazy
    UserRoleDao userRoleDao;

    @Override
    public int updateUser(User user) {
        return userDao.updateByPrimaryKeySelective(user);
    }

    /**
     * 根据传入角色更新
     */
    @Override
    public int updateUserRole(UserRoleDto userRoleDto) {
        int count = 1;
        userRoleDao.deleteByUserId(userRoleDto.getUserId());
        try {
                userRoleDto.getRoleIds().stream().forEach(roleId -> {
                UserRole userRole = new UserRole();
                userRole.setUserId(userRoleDto.getUserId());
                userRole.setRoleId(roleId);
                userRole.setCreatTime(new Date(System.currentTimeMillis()));
                userRoleDao.insertIgnore(userRole);
            });
        }catch (NullPointerException e){
            throw new ResponseException(ResCode.R607.build());
        }
        return count;
    }
}
