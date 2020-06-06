package com.xhjc.rmi.services;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface IHello extends Remote {
    public String sayHelloToSomeBody(String someBodyName) throws RemoteException;
}
