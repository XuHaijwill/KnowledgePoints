package com.xhjc.hello;

import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @Author xhj
 * @Description //TODO
 * @Date 2020/2/8 19:29
 **/
public class SpringIocDemo {

    public static void main(String[] args) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("spring.xml");
        //context.getBean(HelloSpring.class);
        context.getBean("staticTest");
    }
}
