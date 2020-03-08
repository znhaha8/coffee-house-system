package com.wyz.coffee.service;

import com.wyz.coffee.dto.UserDto;
import com.wyz.coffee.entity.User;
import com.wyz.coffee.http.bean.dto.Pagination;

import java.util.List;

/**
 * @Description
 * @Author Jozo
 * @Date 2020/2/15 21:05
 **/
public interface AdminService {
    List<UserDto> findUserByUsername(String username);
    Pagination<UserDto> getUserPage(String username, Integer roleId, int pageNum, int pageSize);
//    Pagination<UserDto> getUserPage(String username, int pageNum, int pageSize);
    int updateCustomer(User user);
}
