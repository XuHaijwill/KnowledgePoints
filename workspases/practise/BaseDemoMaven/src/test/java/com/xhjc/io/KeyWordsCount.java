package com.xhjc.io;

/**
 * @Author xhj
 * @Description //TODO
 * @Date 2020-03-18 22:11
 **/
public class KeyWordsCount {
    //用于类的调用
    private static KeyWordsCount kc;
    //总关键字个数
    private int count = 0;

    //返回类
    public static synchronized KeyWordsCount getCountObject() {
        //若还没有则创建
        if (kc == null) {
            kc = new KeyWordsCount();
        }
        //返回本类
        return kc;
    }

    //线程调用本方法将自己统计的个数加入总个数
    public synchronized void addCount(String str, int count) {
        //System.out.println(str+"线程增加了关键字次数："+count);
        this.count += count;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
