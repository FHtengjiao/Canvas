package com.imooc.study;

import com.imooc.study.dao.CanvasDao;
import com.imooc.study.utils.MyBatisUtil;
import org.apache.ibatis.session.SqlSession;

import java.io.IOException;

public class Test {

    public static void main(String[] args) throws IOException {
        MyBatisUtil.initSqlSessionFactory();
        SqlSession session = MyBatisUtil.getFactory().openSession();
        CanvasDao mapper = session.getMapper(CanvasDao.class);
        mapper.deleteCanvas(1l);
        session.commit();
        session.close();
    }
}
