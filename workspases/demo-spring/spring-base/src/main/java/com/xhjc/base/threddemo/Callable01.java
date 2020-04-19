package com.xhjc.base.threddemo;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * @Author xhj
 * @Description //TODO
 * @Date 2020-04-19 15:13
 **/
public class Callable01  implements Callable<String> {
    @Override
    public String call() throws Exception {
        return "Callable01";
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        FutureTask task = new FutureTask(new Callable01());
        new Thread(task).start();
        System.out.println(task.get());
    }
}
