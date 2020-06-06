package com.xhjc.generic.impl;

import com.xhjc.generic.TestInf;

import java.util.HashMap;
import java.util.Map;

public class TestImpl implements TestInf {
    @Override
    public void testMethod(Map map) {
        System.out.println(map.size());
    }

    public static void main(String[] args) {
        new TestImpl().testMethod((Map) new HashMap().put("HELLO","hello"));
    }
}
