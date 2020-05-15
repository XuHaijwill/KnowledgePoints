package cn.enjoy.service.impl;


import cn.enjoy.dao.UsersMapper;
import cn.enjoy.model.Users;
import cn.enjoy.service.IUserService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

@Service
public class UserServiceImpl implements IUserService {

    @Resource
    private UsersMapper usersMapper;

    @Override
    public boolean login(String username, String passwd) {
        Users users = usersMapper.findByUsernameAndPasswd(username, passwd);
        return users != null;
    }

    @Override
    public boolean register(String username, String passwd) {
        Users users = new Users();
        users.setUsername(username);
        users.setPasswd(passwd);
        int cnt = usersMapper.insertSelective(users);
        return cnt > 0;
    }

    @Override
    //@Transactional
    public void batchAdd(String username, String passwd) {
        Users users = new Users();
        users.setUsername(username);
        users.setPasswd(passwd);
        usersMapper.insertSelective(users);
        int i = 10 /0;
        users = new Users();
        users.setUsername(username+"2");
        users.setPasswd(passwd);
        usersMapper.insertSelective(users);
    }
}