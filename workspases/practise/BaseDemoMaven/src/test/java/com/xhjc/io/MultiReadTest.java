package com.xhjc.io;

import java.io.File;
import java.io.RandomAccessFile;
import java.util.concurrent.CountDownLatch;

/**
 * @Author xhj
 * @Description //TODO
 * @Date 2020-03-18 22:11
 **/
public class MultiReadTest {

    @SuppressWarnings("resource")
    public static void main(String[] args) {
        //开始时间设为0
        long startTime=0;
        //结束时间设为0
        long endTime=0;

    	/*
    	//可手动输入线程数目，调试时注释掉
		Scanner input= new Scanner(System.in);   //为Scanner实例化对象input
        int n=input.nextInt();                   //扫描控制台输入
        final int DOWN_THREAD_NUM = n;
    	*/
        //
        //指定线程数目
        //final成员变量必须在声明的时候初始化或在构造方法中初始化，不能再次赋值。
        final int DOWN_THREAD_NUM = 8;
        //

        //要读取的txt文件路径
        final String OUT_FILE_NAME = "src/8MB.txt";
        //要查找的关键字
        final String keywords = "a";

        //CountDownLatch类位于java.util.concurrent包下，利用它可以实现类似计数器的功能。
        //具体使用方法为：
        //CountDownLatch的构造函数接收一个int类型的参数作为计数器，如果你想等待N个点完成，这里就传入N。
        //当我们调用一次CountDownLatch的countDown方法时，N就会减1，CountDownLatch的await会阻塞当前线程，直到N变成零。
        //在这里，我们设置CountDownLatch的值为DOWN_THREAD_NUM
        CountDownLatch doneSignal = new CountDownLatch(DOWN_THREAD_NUM);

        //RandomAccessFile是Java输入/输出流体系中功能最丰富的文件内容访问类，可以读取文件内容，也可以向文件输出数据
        //与普通的输入/输出流不同的是，RandomAccessFile支持跳到文件任意位置读写数据
        //RandomAccessFile对象包含一个记录指针，用以标识当前读写处的位置
        //当程序创建一个新的RandomAccessFile对象时，该对象的文件记录指针对于文件头（也就是0处）
        //当读写n个字节后，文件记录指针将会向后移动n个字节
        //除此之外，RandomAccessFile可以自由移动该记录指针
        RandomAccessFile[] outArr = new RandomAccessFile[DOWN_THREAD_NUM];

        try{
            //此方法用于获取文件长度，最大只能获取2g的文件大小，因为返回值类型为long
            long length = new File(OUT_FILE_NAME).length();
            //输出文件长度
            System.out.println("文件总长度："+length+"字节，即"+length/1024/1024+"MB");

            //计算每个线程应该读取的字节数
            long numPerThred = length / DOWN_THREAD_NUM;
            System.out.println("共有"+DOWN_THREAD_NUM+"个线程，每个线程读取的字节数："+numPerThred+"字节");

            //计算整个文件整除后剩下的余数
            long left = length % DOWN_THREAD_NUM;

            //获取开始时间
            startTime=System.currentTimeMillis();

            //为每个线程打开一个输入流、一个RandomAccessFile对象
            //让每个线程分别负责读取文件的不同部分
            for (int i = 0; i < DOWN_THREAD_NUM; i++) {
                //rw：以读取、写入方式打开指定文件
                outArr[i] = new RandomAccessFile(OUT_FILE_NAME, "rw");

                //最后一个线程读取指定numPerThred+left个字节
                if (i == DOWN_THREAD_NUM - 1) {
                    //输出其要读的字节范围（测试时应把这句注释掉，因为会影响运行时间的测定）
                    //System.out.println("第"+i+"个线程读取从"+i * numPerThred+"到"+((i + 1) * numPerThred+ left)+"的位置");

                    //ReadThread类用于读取文件，在读取到关键字时，在指定的变量加一
                    new ReadThread(i * numPerThred, (i + 1) * numPerThred + left, 	//开始位置和结束位置
                            outArr[i],	//第i个RandomAccessFile对象
                            keywords,	//关键词
                            doneSignal	//CountDownLatch类
                    ).start();  //线程启动
                }
                //每个线程负责读取一定的numPerThred个字节
                else {
                    //输出其要读的字节范围（测试时应把这句注释掉，因为会影响运行时间的测定）
                    //System.out.println("第"+i+"个线程读取从"+i * numPerThred+"到"+((i + 1) * numPerThred)+"的位置");
                    new ReadThread(i * numPerThred, (i + 1) * numPerThred-1,
                            outArr[i],
                            keywords,
                            doneSignal
                    ).start();
                }
            }
        }catch(Exception e){
            e.printStackTrace();  	//捕获异常
        }

        try {
            //确认所有线程任务完成，开始执行主线程的操作
            doneSignal.await();
            //获取结束时间
            endTime=System.currentTimeMillis();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        //获取关键字的计数值
        KeyWordsCount k = KeyWordsCount.getCountObject();

        System.out.println("指定关键字"+keywords+"出现的次数："+k.getCount());
        System.out.println("程序运行时间："+(endTime-startTime)+"ms");

    }

}
