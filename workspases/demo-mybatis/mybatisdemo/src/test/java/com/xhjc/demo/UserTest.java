package com.xhjc.demo;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;
import org.slf4j.Logger;

import java.io.IOException;
import java.io.InputStream;

/**
 * @Author xhj
 * @Description //TODO
 * @Date 2019/12/25 8:19
 **/


public class UserTest {


    //使用xml方式
    @Test
    public void testXML() {
        String resource = "mybatis-config.xml";
        InputStream inputStream = null;
        try {
            inputStream = Resources.getResourceAsStream(resource);
        } catch (IOException e) {
            e.printStackTrace();
        }
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        SqlSession sqlSession = sqlSessionFactory.openSession();
        User user = sqlSession.selectOne("com.xhjc.demo.UserMapper.selectUser", 1);
        System.out.println("user:{}>>>" + user);
    }

    public void testAnnotation(){

    }
}
