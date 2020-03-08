package com.wyz.coffee.dao;

import com.wyz.coffee.dao.generated.UserRoleMapper;
import com.wyz.coffee.entity.UserRole;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.Set;

/**
 * @Description
 * @Author Jozo
 * @Date 2020/2/13 22:46
 **/
@Repository
public interface UserRoleDao extends UserRoleMapper {
    int deleteByUserId(@Param("userId") Integer userId);
    Set<UserRole> selectByUserId(Integer userId);
    int insertIgnore(UserRole userRole);
}
