package com.imooc.study.serivce;

import com.imooc.study.dao.CategoryDao;
import com.imooc.study.entity.Category;
import com.imooc.study.utils.MyBatisUtil;
import org.apache.ibatis.session.SqlSession;

import java.util.List;

public class CategoryService {

    private SqlSession session;

    public List<Category> findCategories() {
        session = MyBatisUtil.getFactory().openSession();
        List<Category> categories = null;
        try {
            CategoryDao mapper = session.getMapper(CategoryDao.class);
            categories = mapper.findCategories();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
        return categories;
    }

    public void addCategory(Category category) {
        session = MyBatisUtil.getFactory().openSession();
        try {
            CategoryDao mapper = session.getMapper(CategoryDao.class);
            mapper.addCategory(category);
            session.commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    public void updateCategory(Category category) {
        session = MyBatisUtil.getFactory().openSession();
        try {
            CategoryDao mapper = session.getMapper(CategoryDao.class);
            mapper.updateCategory(category);
            session.commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    public void deleteCategory(Long id) {
        session = MyBatisUtil.getFactory().openSession();
        try {
            CategoryDao mapper = session.getMapper(CategoryDao.class);
            mapper.deleteCategory(id);
            session.commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
    }
}
