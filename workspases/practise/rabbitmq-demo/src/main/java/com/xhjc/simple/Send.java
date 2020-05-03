package com.xhjc.simple;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.xhjc.utils.ConnectionUtils;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @Author xhj
 * @Description //TODO
 * @Date 2020-05-02 21:55
 **/
public class Send {
    private static final String QUEUE_NAME="test_simple_queue";

    public static void main(String[] args) throws IOException, TimeoutException {
        Connection connection = ConnectionUtils.getConnection();

        Channel channel = connection.createChannel();

        //创建队列声明
        channel.queueDeclare(QUEUE_NAME, false, false, false, null);
        String msg="hello simple 2 !";

        channel.basicPublish("", QUEUE_NAME, null, msg.getBytes());


        System.out.println("--send msg:"+msg);

        channel.close();
        connection.close();

    }
}
