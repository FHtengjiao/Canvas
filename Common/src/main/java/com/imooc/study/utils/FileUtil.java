package com.imooc.study.utils;

import com.imooc.study.serivce.CanvasService;

import javax.servlet.http.HttpServletRequest;
import java.io.File;

public class FileUtil {
    public static void deletePicById(Long id, CanvasService canvasService, HttpServletRequest request) {
        String picPath = canvasService.findCanvasById(id).getSmallImg();
        String picName = picPath.substring(picPath.lastIndexOf("/"));
        String picRealPath = request.getServletContext().getRealPath("/") + picName;
        try {
            new File(picRealPath).delete();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
