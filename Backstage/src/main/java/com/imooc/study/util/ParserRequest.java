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
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.List;

public class ParserRequest {

    public static void fileUpload(HttpServletRequest request, HttpServletResponse response, String method, CanvasService canvasService) throws ServletException, IOException {
        try {
            ServletFileUpload fileUpload = new ServletFileUpload(new DiskFileItemFactory());
            List<FileItem> fileItems = fileUpload.parseRequest(request);
            Canvas canvas = new Canvas(new Date());
            for (FileItem item: fileItems) {
                if (item.isFormField()) {
                    String name = item.getFieldName();
                    String value = item.getString("utf-8");
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
                        response.sendRedirect("/canvas/list.do");
                        return;
                    }
                } else {
                    if (StringUtils.isNotEmpty(item.getName())) {
                        String filePath = request.getServletContext().getRealPath("/");
                        String uuidName = StringUtils.getRandomFileName(item.getName());
                        String url = filePath + uuidName;
                        canvas.setSmallImg("http://127.0.0.1:8081/img/" + uuidName);
                        InputStream is = item.getInputStream();
                        FileOutputStream fos = new FileOutputStream(url);
                        byte[] buff = new byte[1024];
                        int len = 0;
                        while ((len = is.read(buff)) != -1) {
                            fos.write(buff, 0, len);
                            fos.flush();
                        }
                        is.close();
                        fos.close();
                    } else {
                        if ("add".equals(method)) {
                            response.sendRedirect("/canvas/add.do");
                        }
                    }
                }
            }
            if ("add".equals(method)) {
                canvas.setCreateTime(new Date());
                canvas.setCreator((String) request.getSession().getAttribute("username"));
                canvasService.addCanvas(canvas);
            } else {
                canvasService.updateCanvas(canvas);
            }
            response.sendRedirect("/canvas/list.do");
        } catch (FileUploadException e) {
            e.printStackTrace();
        }
    }
}
