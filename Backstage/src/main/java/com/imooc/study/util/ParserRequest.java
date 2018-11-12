package com.imooc.study.util;

import com.imooc.study.entity.Canvas;
import com.imooc.study.serivce.CanvasService;
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
import java.util.List;

public class ParserRequest {

    public static void fileUpload(HttpServletRequest request, HttpServletResponse response, String method, CanvasService canvasService) throws ServletException, IOException {
        try {
            // 创建fileUpload的对象，用于解析
            ServletFileUpload fileUpload = new ServletFileUpload(new DiskFileItemFactory());
            List<FileItem> fileItems = fileUpload.parseRequest(request);

            // 创建Canvas对象
            Canvas canvas = new Canvas(new Date());

            // 解析出对应的字段并对Canvas对象设置对应的属性
            for (FileItem item: fileItems) {
                if (item.isFormField()) {
                    String name = item.getFieldName();
                    String value = item.getString("utf-8");
                    // 判断字段对应的值是否为空？
                    if (StringUtils.isNotEmpty(value)) {
                        switch (name) {
                            case "id":
                                try {
                                    canvas.setId(Long.parseLong(value));
                                } catch (NumberFormatException e) {
                                    e.printStackTrace();
                                }
                                break;
                            case "name":
                                canvas.setName(value);
                                break;
                            case "category":
                                canvas.setCategory(value);
                                break;
                            case "price":
                                try {
                                    canvas.setPrice(Integer.parseInt(value));
                                } catch (NumberFormatException e) {
                                    e.printStackTrace();
                                }
                                break;
                            case "description":
                                canvas.setDescription(value);
                                break;
                            case "details":
                                canvas.setDetails(value);
                                break;
                        }
                    } else {
                        response.sendRedirect("/canvas/list.do"); //
                        return;
                    }
                } else {
                    // 判断上传的图片是否存在
                    if (StringUtils.isNotEmpty(item.getName())) {
                        // 设置图片储存在服务器的位置
                        String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+"/img/";
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

                        // 设置属性
                        canvas.setSmallImg(basePath + uuidName);
                    } else {
                        //图片不存在，当是新建油画的时候，由于图片不能为空，所有返回添加页面
                        if ("add".equals(method)) {
                            response.sendRedirect("/canvas/add.do");
                            return;
                        }
                    }
                }
            }
            // 执行新建油画时候的逻辑
            if ("add".equals(method)) {
                canvas.setCreateTime(new Date());
                canvas.setCreator((String) request.getSession().getAttribute("username"));
                canvasService.addCanvas(canvas);
            }
            // 执行编辑油画时候的逻辑
            else {
                // 如果用户有新上传油画，执行删除老油画的操作
                if (canvas.getSmallImg() != null) {
                    Long id  = canvas.getId();
                    String picPath = canvasService.findCanvasById(id).getSmallImg();
                    String picName = picPath.substring(picPath.lastIndexOf("/"));
                    String picRealPath = request.getServletContext().getRealPath("/") + picName;
                    try {
                        new File(picRealPath).delete();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                canvasService.updateCanvas(canvas);
            }
            response.sendRedirect("/canvas/list.do");
        } catch (FileUploadException e) {
            e.printStackTrace();
        }
    }
}
