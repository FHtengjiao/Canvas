package com.imooc.study.utils;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;

public class MyBatisUtil {

    private static SqlSessionFactory factory;
    private static ThreadLocal<SqlSession> threadLocal; //用于存放SqlSession
    private final static String RESOURCE = "mybatis-conf.xml";  //mybatis的配置文件

    //初始化SqlSessionFactory
    public static void initSqlSessionFactory() {
        try {
            InputStream is = Resources.getResourceAsStream(RESOURCE);
            factory = new SqlSessionFactoryBuilder().build(is);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    //用于获取SqlSessionFactory
    public static SqlSessionFactory getFactory(){
        return factory;
    }

    //用于关闭SqlSession，释放资源
    public static void close() {
        SqlSession session = threadLocal.get();
        if (session != null) {
            session.close();
            threadLocal.set(null);
        }
    }
}
