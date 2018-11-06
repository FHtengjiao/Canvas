package com.imooc.study.Listener;

import com.imooc.study.utils.MyBatisUtil;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;


/**
 * 通过监听器初始化SqlSessionFactory
 * */
@WebListener("/")
public class SqlSessionFactoryListener implements ServletContextListener {
    public void contextInitialized(ServletContextEvent sce) {
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~start~~~~~~~~~~~~~~~~~~~~~~~~~");
        MyBatisUtil.initSqlSessionFactory();
    }

    public void contextDestroyed(ServletContextEvent sce) {
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~end~~~~~~~~~~~~~~~~~~~~~~~~~");
        MyBatisUtil.close();
    }
}
