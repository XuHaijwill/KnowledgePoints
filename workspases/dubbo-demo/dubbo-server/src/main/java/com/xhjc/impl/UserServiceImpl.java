package com.xhjc.impl;

import com.xhjc.UserService;
import com.xhjc.bean.UserVo;

import java.lang.management.ManagementFactory;
import java.util.Date;

/**
 * @Author xhj
 * @Description //TODO
 * @Date 2020-05-04 7:14
 **/
public class UserServiceImpl implements UserService {
    int port;

    @Override
    public UserVo getUser(Integer id) {
        UserVo u = new UserVo();
        u.setBirthDay(new Date());
        u.setId(id);
        u.setPort(port);
        // 获取当前用户名
        u.setName(ManagementFactory.getRuntimeMXBean().getName());
        if (port == 20880) {
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        return u;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }
}
