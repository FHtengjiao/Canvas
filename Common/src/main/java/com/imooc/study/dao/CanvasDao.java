package com.imooc.study.dao;

import com.imooc.study.entity.Canvas;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CanvasDao {

    List<Canvas> findCanvas();

    Canvas findCanvas(@Param("id") Long id, @Param("category") String category);

    List<Canvas> findCanvas(@Param("category") String category);

    int countCanvas();

    int countCanvas(@Param("category") String category);

    void updateCanvas(@Param("canvas") Canvas canvas);

    void deleteCanvas(@Param("id") Long id);
}
