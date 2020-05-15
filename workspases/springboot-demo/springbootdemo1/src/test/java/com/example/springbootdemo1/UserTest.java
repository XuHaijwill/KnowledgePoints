package com.example.springbootdemo1;

import cn.enjoy.App;
import cn.enjoy.dao.UsersMapper;
import cn.enjoy.model.Users;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

/**
 * @Author xhj
 * @Description //TODO
 * @Date 2020-05-14 18:29
 **/
@SpringBootTest(classes = App.class)
@RunWith(SpringRunner.class)
public class UserTest {

    @Resource
    private UsersMapper usersMapper;

    @Test
    public void testAdd() {
        Users user = new Users() ;
        user.setPasswd("123");
        user.setUsername("enjoy");
        usersMapper.insertSelective(user);
    }

    @Test
    public void testFindUser() {
        Users enjoy = usersMapper.findByUsernameAndPasswd("enjoy", "123");
        System.out.println(enjoy);
    }

}
