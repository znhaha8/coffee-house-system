package com.wyz.coffee.service;

import com.wyz.coffee.entity.Role;
import com.wyz.coffee.http.bean.dto.Pagination;

import java.util.List;
import java.util.Set;

/**
 * @Description
 * @Author Jozo
 * @Date 2020/2/12 17:08
 **/
public interface RoleService {
     int createRole(Role role);
     List<Role> getRoleByName(String roleName);
     Pagination<Role> getRolePage(String roleName, int pageNum, int pageSize);
     List<Role> getAllRoles();
     int updateRoleById(Role role);
     int deleteRole(Integer id);
     List<Role> findRolesByUserId(Integer userId);
     Set<String> selectRoleStringSetByUsername(String username);
}
