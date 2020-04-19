package com.xhjc;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;

/**
 * @Author xhj
 * @Description //TODO
 * @Date 2020-04-18 13:43
 **/
public class Test {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        System.out.print("输入一个子串（1<长度<100）");
        String str = s.nextLine();
        //计算同一字母出现的最大次数
        char[] chars = str.toCharArray();

        System.out.print("输出对应每个字串同一字母连续出现的最大次数" + conut(chars));

    }

    public static Integer conut(char[] c) {
        //定义一个记住最大次数的变量
        int max = 0;
        //定义一个保存出现最多次数的字符
        char cc = 0;
        //循环比较
        for (int i = 0; i < c.length; i++) {
         //定义一个中间值
            int is = 0;
            for (int j = 0; j < c.length - 1; j++) {
               //比较字符
                if (c[i] == c[j + 1]) {
                    is++;
                }
                //比较出现次数大的输出
                if (is > max) {
                    max = is;
                    cc = c[i];
                }
            }
        }
        return max;
    }
}