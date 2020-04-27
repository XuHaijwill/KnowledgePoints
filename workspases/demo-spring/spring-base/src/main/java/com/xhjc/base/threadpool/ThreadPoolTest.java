package com.xhjc.base.threadpool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * @Author xhj
 * @Description //TODO
 * @Date 2020-04-20 7:56
 **/
public class ThreadPoolTest {
    public static void main(String[] args) {
        ExecutorService executorService= Executors.newCachedThreadPool();

        new Thread(new MonitorThreadPoolUtil((ThreadPoolExecutor) executorService,1)).start();

//        Submit 和 execute 方法
//        1、有返回值和无返回值
//        2、Task 不一样 futuretask 一个 task 本身
        
//        executorService.submit(new Runnable() {
//            @Override
//            public void run() {
//                System.out.println(Thread.currentThread().getName());
//                System.out.println("悟空是只猴子");
//                try {
//                    Thread.sleep(10000);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//
//            }
//        });

        executorService.execute(new Runnable() {
            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName());
                System.out.println("悟空是只猴子");
                try {
                    Thread.sleep(10000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        executorService.shutdown();
    }
}
