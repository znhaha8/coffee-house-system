package com.wyz.coffee.controller;

import com.wyz.coffee.dto.LoginDto;
import com.wyz.coffee.entity.User;
import com.wyz.coffee.http.bean.dto.Response;
import com.wyz.coffee.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.Enumeration;

/**
 * @Description
 * @Author Jozo
 * @Date 2020/2/12 11:41
 **/
@RestController
public class UserController {
    @Autowired
    @Lazy
    UserService userService;

    @PostMapping("/login")
    public Response login(@RequestBody LoginDto loginDto) {
        userService.login(loginDto.getUsername(), loginDto.getPassword());
        return Response.success((String)SecurityUtils.getSubject().getPrincipal());
    }

    @GetMapping("/logout")
    public Response logout() {
        userService.logout();
        return Response.success();
    }

    //TODO 初始化角色为customer
    @PostMapping("/register")
    public Response register(@RequestBody User user) {
        userService.createUser(user);
        return Response.success();

    }

    @PutMapping("/changeMyPassword")
    public Response changeMyPassword(@RequestParam("oldPassword") String oldPassword, @RequestParam("newPassword") String newPassword) {
        userService.changeMyPassword(oldPassword, newPassword);
        return Response.success();
    }

    @GetMapping("/getMyInfo")
    public Response<User> getMyInfo() {
        User user = userService.getMyInfo();
        return Response.success(user);
    }

    @PutMapping("/updateByUsername")
    public Response updateMyInfo(@RequestBody User user) {
        User real = new User();
        real.setUsername(user.getUsername());
        real.setNickname(user.getNickname());
        real.setPhone(user.getPhone());
        userService.updateMyInfo(real);
        return Response.success();
    }

    @RequestMapping("/test")
    public void prinTest() {
        System.out.println("test");
        Subject subject = SecurityUtils.getSubject();
        System.out.println(subject.getPrincipals().toString());
        subject.hasRole("admin");
    }

    @RequestMapping(value="/show")
    public String show(HttpSession session) {
        // 获取session中所有的键值
        Enumeration<?> enumeration = session.getAttributeNames();
        // 遍历enumeration中的
        while (enumeration.hasMoreElements()) {
            // 获取session键值
            String name = enumeration.nextElement().toString();
            // 根据键值取session中的值
            Object value = session.getAttribute(name);
            // 打印结果
            System.out.println("<B>" + name + "</B>=" + value + "<br>/n");
        }
        return "查看session成功";
    }

}
