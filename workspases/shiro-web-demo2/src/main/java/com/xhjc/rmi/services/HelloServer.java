package com.xhjc.rmi.services;

import com.xhjc.rmi.services.impl.HelloImpl;

import java.net.MalformedURLException;
import java.rmi.AlreadyBoundException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;

public class HelloServer {
    public static void main(String args[]) {
        try {
            IHello rhello = new HelloImpl();
            LocateRegistry.createRegistry(8888);
            // 如果配置在远程服务器，把地址换成你的ip
            System.setProperty("java.rmi.server.hostname","127.0.0.1");
            Naming.bind("rmi://localhost:8888/RHello", rhello);
            System.out.println(">>>>>INFO:远程IHello对象绑定成功！");
        } catch (RemoteException e) {
            System.out.println("创建远程对象发生异常！");
            e.printStackTrace();
        } catch (AlreadyBoundException e) {
            System.out.println("发生重复绑定对象异常！");
            e.printStackTrace();
        } catch (MalformedURLException e) {
            System.out.println("发生URL畸形异常！");
            e.printStackTrace();
        }
    }
}
