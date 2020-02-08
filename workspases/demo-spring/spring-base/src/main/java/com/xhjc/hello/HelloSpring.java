package com.xhjc.hello;

/**
 * @Author xhj
 * @Description //TODO
 * @Date 2020/2/8 19:16
 **/
public class HelloSpring {

    private String name;
    private int sex;

    public HelloSpring(){

    }

    public HelloSpring(String name,int sex){
        this.name = name;
        this.sex = sex;
    }

    public static HelloSpring build(String type) {
        if ("A".equals(type)) {
            return new HelloSpring("xhjc", 1);
        } else if ("B".equals(type)) {
            return new HelloSpring("diaocan", 0);
        } else {
            throw new IllegalArgumentException("type must 'A' or 'B'");
        }
    }
}
