package com.wyz.coffee.service;

import com.wyz.coffee.dto.UserDto;
import com.wyz.coffee.entity.User;

/**
 * @Description
 * @Author Jozo
 * @Date 2020/2/12 14:24
 **/
public interface UserService {
    Integer createUser(User user); //创建账户

    boolean login(String username, String password);

    boolean logout();

    Integer changeMyPassword(String oldPassword, String newPassword);//修改密码

    UserDto getMyInfo();// 查询自身用户信息

    User findById(Integer id);

    int updateMyInfo(User user);
}
