package com.xhjc.base.aop;

import org.springframework.stereotype.Component;

/**
 * @Author xhj
 * @Description //TODO
 * @Date 2020/3/16 7:46
 **/
@Component("arithmeticCalculator")
public class ArithmeticCalculatorImpl implements ArithmeticCalculator {
    @Override
    public int add(int i, int j) {
        //该方法 添加日志导致代码越来越混乱 难以维护
        //System.out.println("The method add begins with [" + i + "," + j + "]");
        int result = i + j;
        //System.out.println("The method add ends with " + result);
        return result;
    }

    @Override
    public int sub(int i, int j) {
        //System.out.println("The method sub begins with [" + i + "," + j + "]");
        int result = i - j;
        //System.out.println("The method sub ends with " + result);System.out.println("The method sub ends with " + result);
        return result;
    }

    @Override
    public int mul(int i, int j) {
        int result = i * j;
        return result;
    }

    @Override
    public int div(int i, int j) {
        int result = i / j;
        return result;
    }
}
