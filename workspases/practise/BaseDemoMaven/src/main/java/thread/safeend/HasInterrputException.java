package thread.safeend;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @Author xhj
 * @Description  类说明：抛出InterruptedException异常的时候，要注意中断标志位
 * @Date 2020-03-18 22:11
 **/
public class HasInterrputException {

    private static SimpleDateFormat formater
            = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss_SSS");

    private static class UserThread extends Thread{

        public UserThread(String threadName){
            super(threadName);
        }

        @Override
        public void run(){
            String name = Thread.currentThread().getName();
            while(!isInterrupted()){
                try {
                    System.out.println("UseThread:"+formater.format(new Date()));
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                    System.out.println(name+" catch interrput flag is "
                            +isInterrupted()+ " at "
                            +(formater.format(new Date())));
                    interrupt();
                    e.printStackTrace();
                }
                System.out.println(name);
            }
            System.out.println(name+" interrput flag is "
                    +isInterrupted());
        }

        public static void main(String[] args) throws InterruptedException {
            Thread endThread = new UserThread("HasInterrputEx");
            endThread.start();
            System.out.println("Main:"+formater.format(new Date()));
            Thread.sleep(800);
            System.out.println("Main begin interrupt thread:"+formater.format(new Date()));
            endThread.interrupt();
        }

    }


}
