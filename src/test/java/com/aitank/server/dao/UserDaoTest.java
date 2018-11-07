package com.aitank.server.dao;

import com.aitank.server.pojo.User;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import java.util.List;

public class UserDaoTest extends ApplicationTest{

    @Autowired
    private UserDao userDao;
    @Test
    public void selectAllUser() {
        List<User> plist= userDao.selectAllUser();
        System.out.println(plist.size());
        for(User user : plist){
            System.out.println(user);
        }
    }

    @Test
    public void selectUserById() {
        String uuid = "123";
        User user = userDao.selectUserById(uuid);
        System.out.println(user);
    }

    @Test
    public void selectUserByName() {
        String username = "tom";
        User user= userDao.selectUserByName(username);
        System.out.println(user);
    }

    @Test
    @Rollback(false)
    public void deleteUser() {
        String uuid = "123";
        Integer flag = userDao.deleteUser(uuid);
        if(flag == 0 ){
            System.out.println("got it ");
        }else if(flag <0){
            System.out.println("error it");
        }else {
            System.err.println("what the fuck are you doing ? ");
        }
        System.err.println(flag);
    }
}