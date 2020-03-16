package com.xhjc.base.aop;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @Author xhj
 * @Description //TODO
 * @Date 2020/3/16 7:57
 **/
public class AppTest {
    public static void main(String[] args) {
        //使用代理对象调用
//        ArithmeticCalculator arithmeticCalculator = new ArithmeticCalculatorImpl();
//        arithmeticCalculator = new ArithmeticCalculatorLoggingProxy(arithmeticCalculator).getLoggingProxy();
//
//        int result = arithmeticCalculator.add(11, 12);
//		System.out.println("result:" + result);
//
//		result = arithmeticCalculator.div(21, 3);
//		System.out.println("result:" + result);

        ApplicationContext ctx = new ClassPathXmlApplicationContext("spring.xml");
        ArithmeticCalculator arithmeticCalculator = (ArithmeticCalculator) ctx.getBean("arithmeticCalculator");

        System.out.println(arithmeticCalculator.getClass().getName());

        int result = arithmeticCalculator.add(11, 12);
        System.out.println("result:" + result);

        result = arithmeticCalculator.div(1000, 0);
        System.out.println("result:" + result);

    }
}
