package com.xhjc.base.ioc;

/**
 * @Author xhj
 * @Description //TODO
 * @Date 2020-05-03 10:03
 **/
public class HelloSpring {
    private String name;
    private int sex;
    private Hi hi;

    public HelloSpring() {
    }

    public HelloSpring(Hi hi) {
        this.hi = hi;
    }
    public HelloSpring(String name, int sex) {
        this.name = name;
        this.sex = sex;
    }

    public static HelloSpring build(String type) {
        if ("A".equals(type)) {
            return new HelloSpring("luban", 1);
        } else if ("B".equals(type)) {
            return new HelloSpring("diaocan", 0);
        } else {
            throw new IllegalArgumentException("type must 'A' or 'B'");
        }
    }

    public Hi getHi() {
        return hi;
    }

    public void setHi(Hi hi) {
        this.hi = hi;
    }
}
