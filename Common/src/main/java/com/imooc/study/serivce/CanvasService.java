package com.imooc.study.serivce;

import com.imooc.study.dao.CanvasDao;
import com.imooc.study.entity.Canvas;
import com.imooc.study.utils.MyBatisUtil;
import org.apache.ibatis.session.SqlSession;

import java.util.List;

public class CanvasService {

    private SqlSession session;

    /**
     * 从指定的位置开始，查询指定数目的油画
     * */
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

    /**
     * 指定有分类下，从指定的位置开始，查询指定数目的油画
     * */
    public List<Canvas> findCanvasByCategory(String category, Integer skip, Integer size) {
        session = MyBatisUtil.getFactory().openSession();
        List<Canvas> canvas = null;
        try {
            CanvasDao mapper = session.getMapper(CanvasDao.class);
            canvas = mapper.findCanvasByCategory(category, skip, size);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
        return canvas;
    }

    /**
     * 通过id查询油画
     * */
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

    /**
     * 查询有多少油画
     * */
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

    /**
     * 查询分类下有多少油画
     * */
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

    /**
     * 添加油画
     * */
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

    /**
     * 更新油画
     * */
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

    /**
     * 删除油画
     * */
    public void deleteCanvas(Long id) {
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
