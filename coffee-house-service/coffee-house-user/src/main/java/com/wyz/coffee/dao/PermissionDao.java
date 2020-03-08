package com.wyz.coffee.dao;

import com.wyz.coffee.dao.generated.PermissionMapper;
import com.wyz.coffee.entity.Permission;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

/**
 * @Description
 * @Author Jozo
 * @Date 2020/2/13 22:46
 **/
@Repository
public interface PermissionDao extends PermissionMapper {
    List<Permission> selectByName(String name);
    List<Permission> selectByRoleId(Integer userId);
    Set<String> selectPermissionStringSetByUsername(String username);
}
