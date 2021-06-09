package com.xhjc.io;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.concurrent.CountDownLatch;

/**
 * @Author xhj
 * @Description //TODO
 * @Date 2020-03-18 22:11
 **/
public class ReadThread extends Thread {
    //定义字节数组的长度
    private final int BUFF_LEN = 1;

    //定义读取的起始点
    private long start;
    //定义读取的结束点
    private long end;

    //将读取到的字节输出到raf中，randomAccessFile可以理解为文件流
    private RandomAccessFile raf;

    //线程中需要指定的关键字
    private String keywords;
    //此线程读到关键字的次数
    private int curCount = 0;

    //用于确认所有线程计数完成的计数类
    private CountDownLatch doneSignal;

    //构造函数
    public ReadThread(long start, long end, RandomAccessFile raf, String keywords, CountDownLatch doneSignal) {
        this.start = start;    //读取开始位置
        this.end = end;        //读取结束位置
        this.raf = raf;        //第i个RandomAccessFile对象，将读取到的字节输出到raf中
        this.keywords = keywords;        //关键字
        this.doneSignal = doneSignal;    //计数类
    }

    //线程功能：计数
    public void run() {
        try {
            //RandomAccessFile对象
            //void seek(long pos)：将文件记录指针定位到pos位置
            raf.seek(start);

            //计算本线程负责读取文件部分的长度
            long contentLen = end - start;


            //BUFF_LEN为字节数组的长度
            //计算最多需要读取几次就可以完成本线程的读取
            long times = contentLen / BUFF_LEN + 1;
            //输出需要读的次数
            //System.out.println(this.toString() + " 需要读的次数："+times);

            //字节数组
            byte[] buff = new byte[BUFF_LEN];


            int hasRead = 0;
            String result = null;

            //遍历每次读取
            for (int i = 0; i < times; i++) {
                //之前SEEK指定了起始位置，这里用raf.read方法读入指定字节组buff长度的内容
                //返回值为读取到的字节数
                hasRead = raf.read(buff);

                //小于0，则退出循环（到了字节数组的末尾）
                if (hasRead < 0) {
                    break;
                }

                //取出读取的buff字节数组内容
                result = new String(buff, "utf-8");
                //System.out.println(result);

                //计算本次读取中关键字的个数并累加
                int count = this.getCountByKeywords(result, keywords);
                if (count > 0) {
                    this.curCount += count;
                }
            }

            //将本线程读取的关键字个数加入总关键字个数
            KeyWordsCount kc = KeyWordsCount.getCountObject();
            kc.addCount(this.toString(), this.curCount);

            //本线程执行完毕，N--
            doneSignal.countDown();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public int getCountByKeywords(String statement, String key) {
    	/*
    	//split函数是用于按指定字符（串）或正则去分割某个字符串，结果以字符串数组形式返回
    	//.length便是分割的数目，再-1是指定字符串的数目
        return statement.split(key).length-1;
        */
        int count = 0;
        int index = 0;
        while ((index = statement.indexOf(key, index)) != -1) {
            index = index + key.length();
            count++;
        }
        return count;
    }

    public long getStart() {
        return start;
    }

    public void setStart(long start) {
        this.start = start;
    }

    public long getEnd() {
        return end;
    }

    public void setEnd(long end) {
        this.end = end;
    }

    public RandomAccessFile getRaf() {
        return raf;
    }

    public void setRaf(RandomAccessFile raf) {
        this.raf = raf;
    }

    public int getCurCount() {
        return curCount;
    }

    public void setCurCount(int curCount) {
        this.curCount = curCount;
    }

    public CountDownLatch getDoneSignal() {
        return doneSignal;
    }

    public void setDoneSignal(CountDownLatch doneSignal) {
        this.doneSignal = doneSignal;
    }

}
