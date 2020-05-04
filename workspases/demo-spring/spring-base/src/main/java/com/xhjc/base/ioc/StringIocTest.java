package com.xhjc.base.ioc;

import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @Author xhj
 * @Description //TODO
 * @Date 2020-05-03 10:04
 **/
public class StringIocTest {

    public static void main(String[] args) {
        ClassPathXmlApplicationContext context =
                new ClassPathXmlApplicationContext("springIoc.xml");
        context.getBean(HelloSpring.class);
      /*  context.getBean("driver");
        context.getBean(java.sql.Driver.class);*/
    }

}
