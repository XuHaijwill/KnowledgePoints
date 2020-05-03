package com.xhjc.base.volatiles;

import com.xhjc.base.util.TlUtil;

/**
 * @Author xhj
 * @Description //TODO
 * @Date 2020-04-28 7:17
 **/
public class KafkaStart {
    private static volatile boolean isStart = false;

    public  synchronized   void start(){
        if (isStart) {
            throw new RuntimeException();
        }
        System.out.println("初始完成");
        isStart=true;
    }

    public static void main(String[] args) {
        KafkaStart xxxStart=new KafkaStart();
        TlUtil.timeTasks(10, 1, new Runnable() {
            @Override
            public void run() {
//        KafkaStart xxxStart=new KafkaStart();  //测试下
                xxxStart.start();
            }
        });

    }


}
