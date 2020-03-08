package com.wyz.coffee.dao;

import com.wyz.coffee.dao.generated.RoleMapper;
import com.wyz.coffee.entity.Role;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

/**
 * @Description
 * @Author Jozo
 * @Date 2020/2/13 22:46
 **/
@Repository
public interface RoleDao extends RoleMapper {
    List<Role> selectByName(String name);
    List<Role> selectByUserId(Integer userId);
    Set<String> selectRoleStringSetByUsername(String username);
}
