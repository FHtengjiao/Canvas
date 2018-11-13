package com.imooc.study.util;

import com.imooc.study.entity.Canvas;
import com.imooc.study.serivce.CanvasService;
import com.imooc.study.utils.FileUtil;
import com.imooc.study.utils.StringUtils;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ParserRequest {

    public static Map<String, String> fileUpload(HttpServletRequest request) throws ServletException, IOException {
        Map<String, String> map = null;
        try {
            // 创建fileUpload的对象，用于解析
            ServletFileUpload fileUpload = new ServletFileUpload(new DiskFileItemFactory());
            List<FileItem> fileItems = fileUpload.parseRequest(request);

            // 创建Canvas对象
            // Canvas canvas = new Canvas(new Date());
            map = new HashMap<>();

            // 解析出对应的字段并对Canvas对象设置对应的属性
            for (FileItem item : fileItems) {
                if (item.isFormField()) {
                    String name = item.getFieldName();
                    String value = item.getString("utf-8").trim();
                    map.put(name, value);
                } else {
                    // 判断上传的图片是否存在
                    if (StringUtils.isNotEmpty(item.getName())) {
                        // 设置图片储存在服务器的位置
                        String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + "/img/";
                        String path = request.getServletContext().getRealPath("/");

                        // 生成随机的图片名称
                        String uuidName = StringUtils.getRandomFileName(item.getName());
                        String filePath = path + uuidName;

                        // 读取图片
                        InputStream is = item.getInputStream();
                        FileOutputStream fos = new FileOutputStream(filePath);
                        byte[] buff = new byte[1024];
                        int len = 0;
                        while ((len = is.read(buff)) != -1) {
                            fos.write(buff, 0, len);
                            fos.flush();
                        }
                        is.close();
                        fos.close();

                        map.put("smallImg", basePath + uuidName);
                    } else {
                        map.put("smallImg", null);
                    }
                }
            }
        } catch (FileUploadException e) {
            e.printStackTrace();
        }
        System.out.println(map);
        return map;
    }
}
