package thread;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * @Author xhj
 * @Description //TODO
 * @Date 2020-03-18 22:11
 **/
public class Callable01 implements Callable<String> {
    @Override
    public String call() throws Exception {
        return "java学习知识";
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        FutureTask task = new FutureTask(new Callable01());
        //task.run();
        new Thread(task).start();
        System.out.println(task.get());
    }
}
