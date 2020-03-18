package thread;

/**
 * @Author xhj
 * @Description //TODO
 * @Date 2020-03-17 20:07
 **/
public class ThreadDemo extends Thread{

    public static void main(String[] args) {
        ThreadDemo demo = new ThreadDemo();
        demo.start();
    }

    @Override
    public void run(){
        System.out.println("线程启动");
    }

}
