package com.xhjc.utils;

import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @Author xhj
 * @Description
 * @Date 2020-05-02 21:17
 **/
public class ConnectionUtils {

    /**
     * 获取MQ的连接
     * @return
     * @throws IOException
     * @throws TimeoutException
     */
    public static Connection getConnection() throws IOException, TimeoutException {
        //定义一个连接工厂
        ConnectionFactory factory = new ConnectionFactory();

        factory.setHost("212.129.238.44");

        factory.setPort(5672);

        factory.setUsername("xhjc");

        factory.setPassword("123456");

        factory.setVirtualHost("/");

        return factory.newConnection();
    }
}
