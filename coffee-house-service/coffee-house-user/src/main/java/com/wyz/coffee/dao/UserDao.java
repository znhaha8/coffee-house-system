package com.wyz.coffee.dao;

import com.wyz.coffee.dao.generated.UserMapper;
import com.wyz.coffee.entity.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserDao extends UserMapper {
    List<User> selectByUsername(@Param("username") String username);
    List<User> selectByUsernameAndRoleId(@Param("username") String username, @Param("roleId") Integer roleId);
    User selectByUsernameAccurately(String username);
    int updateByUsernameSelective(User user);
}
