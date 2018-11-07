package com.aitank.server.service;

import com.aitank.server.dao.UserDao;
import com.aitank.server.pojo.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UserService {

    @Autowired
    private UserDao userDao;

    public List<User> selectAllOfUsers(){
       return userDao.selectAllUser();
    }

    public User  selectUserByName(String username){
        return userDao.selectUserByName(username);
    }
}
