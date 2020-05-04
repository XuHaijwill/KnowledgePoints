package com.xhjc.impl;

import com.xhjc.UserService;
import com.xhjc.bean.UserVo;

import java.util.Date;

/**
 * @Author xhj
 * @Description //TODO
 * @Date 2020-05-04 7:15
 **/
public class UserServiceImpl2 implements UserService {
    @Override
    public UserVo getUser(Integer id) {
        UserVo u = new UserVo();
        u.setBirthDay(new Date());
        u.setId(id);
        return u;
    }
}
