package com.aitank.server.service;

import com.aitank.server.dao.UserDao;
import com.aitank.server.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service(value = "LoginService")
public class LoginService {

    @Autowired
    UserDao userDao;
    public void LoginSign(){
        System.out.println("LoginSing");
    }
    public void insertUser(){
        User single = new User();
        single.setUsername("root");
        single.setPassword("admin");
        single.setState(true);
        userDao.insertUser(single);
    }
}
