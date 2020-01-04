package com.xhjc.mapper;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;

/**
 * @Author xhj
 * @Description //TODO
 * @Date 2019/12/26 7:42
 **/
public class AppTest {

    public SqlSessionFactory getSqlSessionFactory() throws IOException {
        String resorce ="mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resorce);
        return new SqlSessionFactoryBuilder().build(inputStream);
    }
}
