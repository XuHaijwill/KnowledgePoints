package com.xhjc.base.ioc;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Stack;

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

    //题目1：
//实现个税的计算
//1~5000 税率 0
//5001~8000 3%
//8001~17000 10%
//17001~30000 20%
//30001~40000 25%
//40001~60000 30%
//60001~85000 35%
//85001~      45%
//要求
//1. 逻辑正确，代码优雅
//2. 可扩展性，考虑区间的变化，比如说起征点从5000变成10000等等，或者说85000以上的征税50%。
//这里举个例子，比如说税前10000元，5000部分是不扣税，后面5000，3000扣税3%，2000扣税10%。
    public Double getGeShui(Double salary) {
        Double shuiMoney = 0.00d;
        if (salary < 500) {
            return 0.00;
        } else if (salary > 5000 && salary <= 8000) {
            shuiMoney = (salary - 5000) * 0.03;
        } else if (salary > 8000 && salary <= 170000) {
            shuiMoney = 8000 * 0.03 + (salary - 8000) * 0.1;
        } else if (salary > 17000 && salary <= 30000) {
            shuiMoney = 8000 * 0.03 + (17000 - 8000) * 0.1 + (salary - 17000) * 0.2;
        } else if (salary > 30000 && salary <= 40000) {
            shuiMoney = 8000 * 0.03 + (17000 - 8000) * 0.1 + (30000 - 17000) * 0.2 + (salary - 30000) * 0.25;
        } else if (salary > 40000 && salary <= 60000) {
            shuiMoney = 8000 * 0.03 + (17000 - 8000) * 0.1 + (30000 - 17000) * 0.2 + (40000 - 30000) * 0.25 + (salary - 40000) * 0.3;
        } else if (salary > 60000 && salary <= 85000) {
            shuiMoney = 8000 * 0.03 + (17000 - 8000) * 0.1 + (30000 - 17000) * 0.2 + (40000 - 30000) * 0.25 + (60000 - 40000) * 0.3 + (salary - 60000) * 0.35;
        }else if (salary > 85000) {
            shuiMoney = 8000 * 0.03 + (17000 - 8000) * 0.1 + (30000 - 17000) * 0.2 + (40000 - 30000) * 0.25 + (60000 - 40000) * 0.3 + (85000 - 60000) * 0.35 + (salary - 85000) * 0.35;
        }
        return shuiMoney;
    }


//题目2：请用代码实现表达式是否正确,表达式一定要成对出现，举个例子：
//正确的：[([()])]
//错误的：[)]

    public boolean isValid(String s) {
        if (s.length() % 2 == 1) // 奇数个字符的字符串 显然无法完成括号匹配
            return false;
        Stack<Character> stack = new Stack<>();
        for (int i = 0; i < s.length(); i++) {
            char theChar = s.charAt(i);
            if (theChar == '(' || theChar == '{' || theChar == '[')
                stack.push(theChar);
            else {
                if (stack.empty()) // 栈中已无左括号，此时字符为右括号，无法匹配。
                    return false;
                char preChar = stack.peek();
                if ((preChar == '{' && theChar == '}') || (preChar == '(' && theChar == ')') || (preChar == '[' && theChar == ']'))
                    stack.pop();
                else return false;
            }
        }
        return stack.empty();

    }

}
