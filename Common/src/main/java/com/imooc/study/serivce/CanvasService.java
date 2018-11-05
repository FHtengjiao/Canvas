package com.imooc.study.serivce;

import com.imooc.study.dao.CanvasDao;
import com.imooc.study.entity.Canvas;
import com.imooc.study.utils.MyBatisUtil;
import org.apache.ibatis.session.SqlSession;

import java.util.List;

public class CanvasService {

    private SqlSession session;

    public List<Canvas> findCanvas(Integer skip, Integer size) {
        session = MyBatisUtil.getFactory().openSession();
        List<Canvas> canvas = null;
        try {
            CanvasDao mapper = session.getMapper(CanvasDao.class);
            canvas = mapper.findCanvas(skip, size);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
        return canvas;
    }

    public List<Canvas> findCanvas(String category, Integer skip, Integer size) {
        session = MyBatisUtil.getFactory().openSession();
        List<Canvas> canvas = null;
        try {
            CanvasDao mapper = session.getMapper(CanvasDao.class);
            canvas = mapper.findCanvas(category, skip, size);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
        return canvas;
    }

    public Canvas findCanvasById(Long id) {
        session = MyBatisUtil.getFactory().openSession();
        Canvas canvas = null;
        try {
            CanvasDao mapper = session.getMapper(CanvasDao.class);
            canvas = mapper.findCanvasById(id);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
        return canvas;
    }

    public int countCanvas() {
        session = MyBatisUtil.getFactory().openSession();
        int count = 0;
        try {
            CanvasDao mapper = session.getMapper(CanvasDao.class);
            count = mapper.countCanvas();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
        return count;
    }

    public int countCanvas(String category) {
        session = MyBatisUtil.getFactory().openSession();
        int count = 0;
        try {
            CanvasDao mapper = session.getMapper(CanvasDao.class);
            count = mapper.countCanvas(category);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
        return count;
    }

    public void addCanvas(Canvas canvas) {
        session = MyBatisUtil.getFactory().openSession();
        try {
            CanvasDao mapper = session.getMapper(CanvasDao.class);
            mapper.addCanvas(canvas);
            session.commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    public void updateCanvas(Canvas canvas) {
        session = MyBatisUtil.getFactory().openSession();
        try {
            CanvasDao mapper = session.getMapper(CanvasDao.class);
            mapper.updateCanvas(canvas);
            session.commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    void deleteCanvas(Long id) {
        session = MyBatisUtil.getFactory().openSession();
        try {
            CanvasDao mapper = session.getMapper(CanvasDao.class);
            mapper.deleteCanvas(id);
            session.commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
    }
}
