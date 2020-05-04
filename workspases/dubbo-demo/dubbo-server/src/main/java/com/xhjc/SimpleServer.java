package com.xhjc;

import com.alibaba.dubbo.config.ApplicationConfig;
import com.alibaba.dubbo.config.ProtocolConfig;
import com.alibaba.dubbo.config.RegistryConfig;
import com.alibaba.dubbo.config.ServiceConfig;
import com.xhjc.impl.UserServiceImpl;

import java.io.IOException;

/**
 * @Author xhj
 * @Description //TODO
 * @Date 2020-05-04 7:17
 **/
public class SimpleServer {
   /* public void openServer(int port) {
        // 构建应用
        ApplicationConfig config = new ApplicationConfig();
        config.setName("simple-app");


        // 通信协议
        ProtocolConfig protocolConfig = new ProtocolConfig("dubbo", port);
        protocolConfig.setThreads(200);

        ServiceConfig<UserService> serviceConfig = new ServiceConfig();
        serviceConfig.setApplication(config);
        serviceConfig.setProtocol(protocolConfig);
        serviceConfig.setRegistry(new RegistryConfig("zookeeper://127.0.0.1:2181"));
        serviceConfig.setInterface(UserService.class);
        UserServiceImpl ref = new UserServiceImpl();
        serviceConfig.setRef(ref);

        serviceConfig.export();
        System.out.println("服务已开启!端口:"+serviceConfig.getExportedUrls().get(0).getPort());
        ref.setPort(serviceConfig.getExportedUrls().get(0).getPort());

    }


    public static void main(String[] args) throws IOException {
        new SimpleServer().openServer(-1);
        System.in.read();
    }*/

   public void openServer(int port){
       ServiceConfig serviceConfig = new ServiceConfig();
       serviceConfig.setInterface(UserService.class);
       serviceConfig.setProtocol(new ProtocolConfig("dubbo",port));
       serviceConfig.setRegistry(new RegistryConfig(RegistryConfig.NO_AVAILABLE));
       serviceConfig.setApplication(new ApplicationConfig("simple-app"));
       serviceConfig.setRef(new UserServiceImpl());
       serviceConfig.export();
       System.out.println("服务已开启:" + port );
   }

    public static void main(String[] args) throws IOException {
        new SimpleServer().openServer(-1);
        System.in.read();
    }

}
