package com.wyz.coffee.service.impl;

import com.wyz.coffee.dao.UserRoleDao;
import com.wyz.coffee.entity.UserRole;
import com.wyz.coffee.service.UserRoleService;
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
public class UserRoleServiceImpl implements UserRoleService {

    @Autowired
    @Lazy
    private UserRoleDao userRoleDao;

    @Override
    public int insert(UserRole userRole) {
        userRole.setCreatTime(new Date(System.currentTimeMillis()));
        return userRoleDao.insert(userRole);
    }

    @Override
    public int updateById(UserRole userRole) {
        return userRoleDao.updateByPrimaryKeySelective(userRole);
    }

}
