package thread.vola;

import com.xiangxue.tools.SleepTools;

/**
 * @Author xhj
 * @Description 演示violate无法提供操作的原子性
 * @Date 2020-03-18 22:11
 **/
public class VolatileUnsafe {

    private static class VolatileVar implements Runnable {

        private volatile int a = 0;

        @Override
        public void run() {
            String threadName = Thread.currentThread().getName();
            a = a++;
            System.out.println(threadName+":======"+a);
            SleepTools.ms(100);
            a = a+1;
            System.out.println(threadName+":======"+a);
        }
    }

    public static void main(String[] args) {
        VolatileVar v = new VolatileVar();

        Thread thread = new Thread(v);
        Thread thread2 = new Thread(v);
        Thread thread3 = new Thread(v);
        Thread thread4 = new Thread(v);

        thread.start();
        thread2.start();
        thread3.start();
        thread4.start();

    }
}
