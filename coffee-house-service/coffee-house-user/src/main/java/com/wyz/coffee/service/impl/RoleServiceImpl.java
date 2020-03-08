package com.wyz.coffee.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.wyz.coffee.constans.ResCode;
import com.wyz.coffee.dao.RoleDao;
import com.wyz.coffee.entity.Role;
import com.wyz.coffee.http.bean.dto.Pagination;
import com.wyz.coffee.http.exception.ResponseException;
import com.wyz.coffee.service.RoleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Set;

/**
 * @Description
 * @Author Jozo
 * @Date 2020/2/13 23:18
 **/
@Service
@Slf4j
public class RoleServiceImpl implements RoleService {
    @Autowired
    @Lazy
    private RoleDao roleDao;
    @Override
    public int createRole(Role role) {
        role.setCreateTime(new Date(System.currentTimeMillis()));
        return roleDao.insert(role);
    }

    @Override
    public List<Role> getRoleByName(String name) {
        return roleDao.selectByName(name);
    }

    @Override
    public Pagination<Role> getRolePage(String roleName, int pageNum, int pageSize) {
        Page<Role> page = PageHelper.startPage(pageNum, pageSize).doSelectPage(() -> roleDao.selectByName(roleName));
        Pagination<Role> res = new Pagination<>();
        BeanUtils.copyProperties(page, res);
        return res;
    }

    @Override
    public List<Role> getAllRoles() {
        return roleDao.selectByName("");
    }

    @Override
    public int updateRoleById(Role role) {
        return roleDao.updateByPrimaryKeySelective(role);
    }

    @Override
    public int deleteRole(Integer id) {
        Role role = roleDao.selectByPrimaryKey(id);
        if(role == null || role.getName() == null || role.getName().equals("superAdmin") || role.getName().equals("admin")){
            throw new ResponseException(ResCode.R605.build());
        }
        return roleDao.deleteByPrimaryKey(role.getId());
    }

    @Override
    public List<Role> findRolesByUserId(Integer userId) {
        return roleDao.selectByUserId(userId);
    }

    @Override
    public Set<String> selectRoleStringSetByUsername(String username) {
        return roleDao.selectRoleStringSetByUsername(username);
    }

}
