package com.wyz.coffee.controller;


import com.wyz.coffee.dto.UserDto;
import com.wyz.coffee.entity.User;
import com.wyz.coffee.http.bean.dto.Pagination;
import com.wyz.coffee.http.bean.dto.Response;
import com.wyz.coffee.service.AdminService;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Description
 * @Author Jozo
 * @Date 2020/2/14 20:41
 **/
@RestController
@RequestMapping("/admin")
@RequiresRoles(value = {"admin","superAdmin"}, logical = Logical.OR)
public class AdminController {
    @Autowired
    @Lazy
    private AdminService adminService;

    /**
     * 根据username查询用户信息（模糊查询）
     * @param username
     * @return
     */
    @GetMapping("/findUser")
    public Response<List<UserDto>> findUser(@RequestParam(value = "username",defaultValue = "") String username){
        List<UserDto> users = adminService.findUserByUsername(username);
        return Response.success(users);
    }

    @GetMapping("/getUserPage")
    public Response<Pagination<UserDto>> getUserPage(@RequestParam(value = "username",defaultValue = "") String username,
                                                     @RequestParam(value = "roleId", defaultValue = "-1") Integer roleId,
                                               @RequestParam(value = "pageNum",defaultValue = "1")int pageNum,
                                               @RequestParam(value = "pageSize",defaultValue = "20")int pageSize){
        Pagination<UserDto> userPage = adminService.getUserPage(username, roleId, pageNum, pageSize);
        return Response.success(userPage);
    }
    /**
     * 修改顾客信息
     */
    @PutMapping("/updateCustomer")
    public Response updateCustomer(User user){
        user.setCreateTime(null);
        user.setSalt(null);
        user.setUsername(null);
        adminService.updateCustomer(user);
        return Response.success();
    }


}
