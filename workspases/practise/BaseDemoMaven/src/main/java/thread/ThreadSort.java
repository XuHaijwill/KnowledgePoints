package thread;

/**
 * @Author xhj
 * @Description //TODO
 * @Date 2020-03-17 20:33
 **/
public class ThreadSort {

    public static void main(String[] args) throws InterruptedException {
        Thread thread1 = new Thread(()->{
            System.out.println("Thread1");
        });
        Thread thread2 = new Thread(()->{
            System.out.println("Thread2");
        });
        Thread thread3 = new Thread(()->{
            System.out.println("Thread3");
        });
        thread1.start();
        thread1.join();
        thread2.start();
        thread2.join();
        thread3.start();
        thread3.join();
    }
}
