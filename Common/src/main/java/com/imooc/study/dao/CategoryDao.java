package com.imooc.study.dao;

import com.imooc.study.entity.Category;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CategoryDao {

    Category findCategories(@Param("id") Long id);

    List<Category> findCategories();

    void addCategory(@Param("category") Category category);

    void updateCategory(@Param("category") Category category);

    void deleteCategory(@Param("id") Long id);
}
