package com.xhjc.client;

import com.alibaba.dubbo.config.ApplicationConfig;
import com.alibaba.dubbo.config.ReferenceConfig;
import com.xhjc.UserService;

/**
 * @Author xhj
 * @Description //TODO
 * @Date 2020-05-04 7:25
 **/
public class YoungClient {


    // URL 远程服务的调用地址
    public UserService buildService(String url) {
        // 构建一个引用对象
        ReferenceConfig<UserService> referenceConfig = new ReferenceConfig<>();
        referenceConfig.setApplication(new ApplicationConfig("young-app"));
        referenceConfig.setInterface(UserService.class);
        referenceConfig.setUrl(url);
        return referenceConfig.get();
    }

    public static void main(String[] args) {
        YoungClient client = new YoungClient();
        UserService uservice = client.buildService("dubbo://192.168.43.2:20882/com.xhjc.UserService");
        System.out.println(uservice.getUser(12));
    }

}
