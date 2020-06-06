package com.xhjc.rmi.services.impl;

import com.xhjc.rmi.services.IHello;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class HelloImpl  extends UnicastRemoteObject implements IHello {

    public HelloImpl() throws RemoteException {
        super();
    }

    @Override
    public String sayHelloToSomeBody(String someBodyName) throws RemoteException {
        System.out.println("Connected sucessfully!");
        return "你好，" + someBodyName + "!";
    }
}
