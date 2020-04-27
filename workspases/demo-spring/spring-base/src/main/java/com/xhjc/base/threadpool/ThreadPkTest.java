package com.xhjc.base.threadpool;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * @Author xhj
 * @Description //TODO
 * @Date 2020-04-20 7:47
 **/
public class ThreadPkTest {

    public static void main(String[] args) throws InterruptedException {
        Long start = System.currentTimeMillis();
        final List<Integer> l=new ArrayList<Integer>();
        final Random random=new Random();
        /**
         *创建10000个线程做一件事
         * @param args
         * @throws InterruptedException
         */
        for(int i=0;i<10000;i++){
         Thread thread = new Thread(){
             public void run(){
                 l.add(random.nextInt());
             }
         };
         thread.start();
         thread.join();
        }
        System.out.println(System.currentTimeMillis() - start);
        System.out.println(l.size());


    }
}
