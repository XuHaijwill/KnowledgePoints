package com.xhjc.base.threddemo;

/**
 * @Author xhj
 * @Description //TODO
 * @Date 2020-04-19 14:33
 **/
public class Thread01 extends Thread{

    public static void main(String[] args) {
        new Thread01().run();
    }

    @Override
    public void run(){
        System.out.println("线程01");
    }


}
