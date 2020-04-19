package com.xhjc.base.threddemo;

/**
 * @Author xhj
 * @Description //TODO
 * @Date 2020-04-19 15:10
 **/
public class Runable01  implements Runnable{

    public static void main(String[] args) {
        new Thread(new Runable01()).start();
    }
    @Override
    public void run() {
        System.out.println("Runable01");
    }
}
