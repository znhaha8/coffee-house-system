package com.wyz.coffee.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.wyz.coffee.constans.ResCode;
import com.wyz.coffee.dao.RoleDao;
import com.wyz.coffee.dao.UserDao;
import com.wyz.coffee.dto.UserDto;
import com.wyz.coffee.entity.Role;
import com.wyz.coffee.entity.User;
import com.wyz.coffee.http.bean.dto.Pagination;
import com.wyz.coffee.http.exception.ResponseException;
import com.wyz.coffee.service.AdminService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @Description
 * @Author Jozo
 * @Date 2020/2/15 21:06
 **/
@Service
@Slf4j
public class AdminServiceImpl implements AdminService {
    @Autowired
    @Lazy
    UserDao userDao;
    @Autowired
    @Lazy
    RoleDao roleDao;

    @Override
    public List<UserDto> findUserByUsername(String username) {
        List<User> users = userDao.selectByUsername(username);
        List<UserDto> userDtos = users.stream().map(user -> {
            UserDto userDto = new UserDto();
            BeanUtils.copyProperties(user, userDto);
            userDto.setRoles(roleDao.selectByUserId(userDto.getId()));
            return userDto;
        }).collect(Collectors.toList());
        return userDtos;
    }

//    @Override
//    public Pagination<UserDto> getUserPage(String username, int pageNum, int pageSize){
//        Page<User> pages = PageHelper.startPage(pageNum, pageSize).doSelectPage(() -> userDao.selectByUsername(username));
//        List<UserDto> userDtos = pages.getResult().stream().map(user -> {
//            UserDto userDto = new UserDto();
//            BeanUtils.copyProperties(user, userDto);
//            userDto.setRoles(roleDao.selectByUserId(userDto.getId()));
//            return userDto;
//        }).collect(Collectors.toList());
//        Pagination<UserDto> result = new Pagination<>();
//        BeanUtils.copyProperties(pages, result, "result");
//        result.setResult(userDtos);
//        return result;
//    }

    @Override
    public Pagination<UserDto> getUserPage(String username, Integer roleId, int pageNum, int pageSize) {
        Page<User> pages = PageHelper.startPage(pageNum, pageSize).doSelectPage(() -> userDao.selectByUsernameAndRoleId(username, roleId));
        List<UserDto> userDtos = pages.getResult().stream().map(user -> {
            UserDto userDto = new UserDto();
            BeanUtils.copyProperties(user, userDto);
            userDto.setRoles(roleDao.selectByUserId(userDto.getId()));
            return userDto;
        }).collect(Collectors.toList());
        Pagination<UserDto> result = new Pagination<>();
        BeanUtils.copyProperties(pages, result, "result");
        result.setResult(userDtos);
        return result;
    }

    @Override
    public int updateCustomer(User user) {
        List<Role> roles = roleDao.selectByUserId(user.getId());
        if (roles.size() > 1 || !roles.get(0).getName().equals("vip")) {
            throw new ResponseException(ResCode.R602.build());
        }
        return userDao.updateByPrimaryKeySelective(user);
    }
}
