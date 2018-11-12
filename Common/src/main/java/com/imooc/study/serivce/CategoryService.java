package com.imooc.study.serivce;

import com.imooc.study.dao.CategoryDao;
import com.imooc.study.entity.Category;
import com.imooc.study.utils.MyBatisUtil;
import org.apache.ibatis.session.SqlSession;

import java.util.List;

public class CategoryService {

    private SqlSession session;

    /**
     * 通过id查询数据库中的分类
     * */
    public Category findCategoryById(Long id) {
        session = MyBatisUtil.getFactory().openSession();
        Category category = null;
        try {
            CategoryDao mapper = session.getMapper(CategoryDao.class);
            category = mapper.findCategories(id);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
        return category;
    }

    /**
     * 查询数据库中所有的分类
     * */
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

    /**
     * 新增分类
     * */
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

    /**
     * 更新分类
     * */
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

    /**
     * 删除分类
     * */
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
