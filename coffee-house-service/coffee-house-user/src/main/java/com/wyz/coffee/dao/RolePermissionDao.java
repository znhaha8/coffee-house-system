package com.wyz.coffee.dao;

import com.wyz.coffee.dao.generated.RolePermissionMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * @Description
 * @Author Jozo
 * @Date 2020/2/13 22:46
 **/
@Repository
public interface RolePermissionDao extends RolePermissionMapper {
    int deleteByRoleIdAndPermissionId(@Param("roleId") Integer roleId, @Param("permissionId") Integer permissionId);
}
