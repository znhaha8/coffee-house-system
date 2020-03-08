package com.wyz.coffee.service.impl;

import com.wyz.coffee.constans.ResCode;
import com.wyz.coffee.dao.UserDao;
import com.wyz.coffee.dto.UserDto;
import com.wyz.coffee.entity.Role;
import com.wyz.coffee.entity.User;
import com.wyz.coffee.http.exception.ResponseException;
import com.wyz.coffee.service.PermissionService;
import com.wyz.coffee.service.RoleService;
import com.wyz.coffee.service.UserService;
import com.wyz.coffee.util.PasswordHelper;
import com.wyz.coffee.util.StringUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @Description
 * @Author Jozo
 * @Date 2020/2/12 14:26
 **/
@Service
@Slf4j
public class UserServiceImpl implements UserService {
    @Autowired
    @Lazy
    private UserDao userDao;
    @Autowired
    @Lazy
    private RoleService roleService;
    @Autowired
    @Lazy
    private PermissionService permissionService;

    @Override
    public boolean login(String username, String password) {
        UsernamePasswordToken token = new UsernamePasswordToken(username, password);
        //token.setRememberMe(true);
        // 进行验证，这里可以捕获异常，然后返回对应信息
        try {
            SecurityUtils.getSubject().login(token);
        } catch (AuthenticationException e) {
            throw new ResponseException(ResCode.R600.build());
        }
        return true;
    }

    @Override
    public boolean logout() {
        SecurityUtils.getSubject().logout();
        return true;
    }

    @Override
    public Integer createUser(User user) {
        User dbuser = userDao.selectByUsernameAccurately(user.getUsername());
        if (dbuser != null) {
            throw new ResponseException(ResCode.R603.build());
        }
        PasswordHelper passwordHelper = new PasswordHelper();
        user.setSalt(passwordHelper.createSalt());
        String credentials = passwordHelper.createCredentials(user.getPassword(), user.getSalt());
        user.setPassword(credentials);
        user.setCreateTime(new Date(System.currentTimeMillis()));
        return userDao.insertSelective(user);
    }

    @Override
    //TODO
    public Integer changeMyPassword(String oldPassword, String newPassword) {
        String username;
        try {
            username = (String) SecurityUtils.getSubject().getPrincipal();
        } catch (NullPointerException e) {
            throw new ResponseException(ResCode.R602.build());
        }
        User user = userDao.selectByUsernameAccurately(username);
        PasswordHelper passwordHelper = new PasswordHelper();
        boolean check = passwordHelper.checkCredentials(oldPassword, user.getSalt(), user.getPassword());
        if (!check) {
            throw new ResponseException(ResCode.R601.build());
        }
        newPassword = passwordHelper.createCredentials(newPassword, user.getSalt());
        user.setPassword(newPassword);
        return userDao.updateByPrimaryKey(user);

    }

    @Override
    public UserDto getMyInfo() {
        String username;
        try {
            username = (String) SecurityUtils.getSubject().getPrincipal();
            if(StringUtils.isEmpty(username)) throw new NullPointerException();
        } catch (NullPointerException e) {
            throw new ResponseException(ResCode.R604.build());
        }
        UserDto userDto = new UserDto();
        User user = userDao.selectByUsernameAccurately(username);
        BeanUtils.copyProperties(user, userDto);
        List<Role> roles = roleService.findRolesByUserId(user.getId());
        userDto.setPassword(null);
        userDto.setRoles(roles);
        return userDto;
    }

    @Override
    public User findById(Integer id) {
        return userDao.selectByPrimaryKey(id);
    }


    @Override
    public int updateMyInfo(User user) {
        String username = (String) SecurityUtils.getSubject().getPrincipal();
        if (!username.equals(user.getUsername())) {
            throw new ResponseException(ResCode.R602.build());
        }
        return userDao.updateByUsernameSelective(user);
    }


}
