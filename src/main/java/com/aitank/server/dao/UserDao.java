package com.aitank.server.dao;

import com.aitank.server.pojo.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserDao {

     List<User> selectAllUser();
     User  selectUserById(@Param("uuid") String uuid);
     User  selectUserByName(@Param("username")String username);
     Integer deleteUser(@Param("uuid") String uuid);
     Integer insertUser(User user);
}
