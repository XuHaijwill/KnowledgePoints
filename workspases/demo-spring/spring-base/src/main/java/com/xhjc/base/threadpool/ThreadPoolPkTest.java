package com.xhjc.base.threadpool;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * @Author xhj
 * @Description //TODO
 * @Date 2020-04-20 7:52
 **/
public class ThreadPoolPkTest {
    public static void main(String[] args) throws InterruptedException {
        Long start= System.currentTimeMillis();
        final List<Integer> list=new ArrayList<Integer>();
        ExecutorService executorService =  Executors.newSingleThreadExecutor();
        final Random random=new Random();
        for(int i=0;i<10000;i++){
            final int j=i;
            executorService.execute(new Runnable() {
                @Override
                public void run() {
                    list.add(j);
                }
            });
        }
        executorService.shutdown();
        executorService.awaitTermination(1, TimeUnit.DAYS);
        System.out.println(System.currentTimeMillis()-start);
        System.out.println(list.size());
    }
}
