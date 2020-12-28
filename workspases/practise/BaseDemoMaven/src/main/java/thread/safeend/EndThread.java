package thread.safeend;

/**
 * @Author xhj
 * @Description 如何安全的中断线程
 * @Date 2020-03-18 22:11
 **/
public class EndThread {
    private static class UseThread extends Thread{

        public UseThread(String name) {
            super(name);
        }

        @Override
        public void run() {
            String threadName = Thread.currentThread().getName();
            while(true) {
                System.out.println(threadName+" is run!");
            }
            //System.out.println(threadName+" interrput flag is "+isInterrupted());
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Thread endThread = new UseThread("endThread");
        endThread.start();
        Thread.sleep(20);
        endThread.interrupt();

    }
}
