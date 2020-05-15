package cn.enjoy.service;


public interface IUserService {
    boolean login(String username, String passwd);
    boolean register(String username, String passwd);
    void batchAdd(String username, String passwd);
}