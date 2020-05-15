package cn.enjoy.controller;

import cn.enjoy.service.IUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @Author xhj
 * @Description //TODO
 * @Date 2020-05-14 17:44
 **/
@RestController
public class UserController {

    private final Logger logger = LoggerFactory.getLogger(UserController.class);

    @Resource
    private IUserService iUserService;

    @RequestMapping("/hello")
    public String sayHello(){
        logger.info("hello>>>");
        return "hello";
    }

    @RequestMapping("/login")
    public String login(String username,String passwd) {
        boolean login = iUserService.login(username, passwd);
        if(login) {
            return "登陆成功";
        }else {
            return  "登陆失败";
        }
    }

    @RequestMapping("/register")
    public String register(String username,String passwd) {
        boolean login = iUserService.register(username, passwd);
        if(login) {
            return "注册成功";
        }else {
            return  "注册失败";
        }
    }

    @RequestMapping("/batchAdd")
    @Transactional
    public String batchAdd(String username,String passwd) {
        iUserService.batchAdd(username, passwd);
        return "成功";
    }

}
