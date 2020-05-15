package cn.enjoy.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author xhj
 * @Description //TODO
 * @Date 2020-05-14 18:33
 **/

@RestController
public class BaseController {

    @RequestMapping("/404.do")
    public String error_404() {
        return "你要找的页面，被lison偷吃了！";
    }

}
