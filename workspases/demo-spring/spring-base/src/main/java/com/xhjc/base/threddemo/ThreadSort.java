package com.xhjc.base.threddemo;

/**
 * @Author xhj
 * @Description //TODO
 * @Date 2020-04-19 14:36
 **/
public class ThreadSort {

    public static void main(String[] args) throws InterruptedException {
        Thread thread1 = new Thread(() ->{
            System.out.println("thread1");
        });

        Thread thread2 = new Thread(() ->{
            System.out.println("thread2");
        });

        Thread thread3 = new Thread(() ->{
            System.out.println("thread3");
        });

        thread1.start();
        thread1.join();
        thread2.start();
        thread2.join();
        thread3.start();
        thread3.join();
    }


}
