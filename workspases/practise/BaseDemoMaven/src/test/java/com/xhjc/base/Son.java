package com.xhjc.base;

/**
 * @Author xhj
 * @Description //TODO
 * @Date 2020-03-18 22:11
 **/
public class Son extends Father{
    public String SStr1 = "Son1";
    protected String SStr2 = "Son2";
    private String SStr3 = "Son3";

    {
        System.out.println("Son common block be called");
    }

    static {
        System.out.println("Son static block be called");
    }

    public Son() {
        System.out.println("Son constructor be called");
    }

    public static void main(String[] args) {
        new Son();
        System.out.println();
        new Son();
        int[] a = new int[]{1,2,3,4,5};
    }

}
