package cn.enjoy.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @Author xhj
 * @Description //TODO
 * @Date 2020-05-15 15:31
 **/
@Controller
@RequestMapping("/tpl")
public class ThymeleafController {
    @RequestMapping("/testThymeleaf")
    public String testThymeleaf(ModelMap map) {
        // 设置属性
        map.addAttribute("name", "enjoy");
        // testThymeleaf：为模板文件的名称
        // 对应src/main/resources/templates/testThymeleaf.html
        return "testThymeleaf";
    }
}
