package com.imooc.study.dao;

import com.imooc.study.entity.Canvas;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CanvasDao {

    List<Canvas> findCanvas(@Param("skip") Integer skip, @Param("size") Integer size);

    List<Canvas> findCanvasByCategory(@Param("category") String category, @Param("skip") Integer skip, @Param("size") Integer size);

    Canvas findCanvasById(@Param("id") Long id);

    Canvas findImg(@Param("id") Long id);

    int countCanvas();

    int countCanvas(@Param("category") String category);

    void addCanvas(@Param("canvas") Canvas canvas);

    void updateCanvas(@Param("canvas") Canvas canvas);

    void deleteCanvas(@Param("id") Long id);
}
