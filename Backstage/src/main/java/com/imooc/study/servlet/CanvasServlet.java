package com.imooc.study.servlet;

import com.imooc.study.entity.Canvas;
import com.imooc.study.entity.Category;
import com.imooc.study.serivce.CanvasService;
import com.imooc.study.serivce.CategoryService;
import com.imooc.study.utils.StringUtils;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;
import java.util.List;


@WebServlet(name="CanvasServlet", urlPatterns = {
        "/canvas/list.do",
        "/canvas/addPrompt.do",
        "/canvas/add.do",
        "/canvas/editPrompt.do",
        "/canvas/edit.do",
        "/canvas/delete.do"
})
public class CanvasServlet extends HttpServlet {

    private CanvasService canvasService;
    private CategoryService categoryService;

    @Override
    public void init() throws ServletException {
        canvasService = new CanvasService();
        categoryService = new CategoryService();
    }

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if ("/canvas/list.do".equals(request.getServletPath())) {

            /* 计算和设置油画相关的参数 */
            // 获取参数，当前页数和类别
            String pageStr = request.getParameter("pageStr");
            String category = request.getParameter("category");

            // 初始化从数据库获取的参数
            List<Canvas> canvas = null;
            int canvasCount = 0;

            // 默认page页码为1，获取用户请求的页码数
            int page = 1;
            if (null != pageStr) {
                try {
                    page = Integer.parseInt(pageStr);
                } catch (NumberFormatException e) {
                    // 格式化参数，出错就报500错误
                    request.getRequestDispatcher("/WEB-INF/views/error/500.jsp").forward(request, response);
                }
            }

            // 判断类别是否为空，为空就获取所有的油画和油画数，否则获取目标类别的油画和油画数
            if (null != category) {
                canvas = canvasService.findCanvasByCategory(category, (page - 1) * 10, 10);
                canvasCount = canvasService.countCanvas(category);
            } else {
                canvas = canvasService.findCanvas((page - 1) * 10, 10);
                canvasCount = canvasService.countCanvas();
            }

            // 计算总共的页面
            int totalPage = canvasCount % 10 == 0 ? canvasCount / 10 : canvasCount / 10 + 1;

            request.setAttribute("totalPage", totalPage);
            request.setAttribute("canvases", canvas);
            request.setAttribute("page", page);


            /* 计算和设置类别相关的参数 */

            List<Category> categories = categoryService.findCategories();
            request.setAttribute("categories", categories);

            request.getRequestDispatcher("/WEB-INF/views/biz/canvas_list.jsp").forward(request, response);
        } else if ("/canvas/addPrompt.do".equals(request.getServletPath())) {
            List<Category> categories = categoryService.findCategories();
            request.setAttribute("categories", categories);
            request.getRequestDispatcher("/WEB-INF/views/biz/add_canvas.jsp").forward(request, response);
        } else if ("/canvas/add.do".equals(request.getServletPath())) {
            try {
                ServletFileUpload fileUpload = new ServletFileUpload(new DiskFileItemFactory());
                List<FileItem> fileItems = fileUpload.parseRequest(request);
                Canvas canvas = new Canvas((String)request.getSession().getAttribute("username"), new Date(), new Date());
                for (FileItem item:
                     fileItems) {
                    if (item.isFormField()) {
                        String name = item.getFieldName();
                        String value = item.getString("utf-8");
                        if (StringUtils.isNotEmpty(value)) {
                            switch (name) {
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
                        canvas.setSmallImg(item.get());
                    }
                }
                canvasService.addCanvas(canvas);
                response.sendRedirect("/canvas/list.do");
            } catch (FileUploadException e) {
                e.printStackTrace();
            }
        } else if ("/canvas/editPrompt.do".equals(request.getServletPath())) {
            String idStr = request.getParameter("id");
            if (StringUtils.isNotEmpty(idStr)) {
                try {
                    Canvas canvas = canvasService.findCanvasById(Long.parseLong(idStr));
                    List<Category> categories = categoryService.findCategories();
                    request.setAttribute("canvas", canvas);
                    request.setAttribute("categories", categories);
                    request.getRequestDispatcher("/WEB-INF/views/biz/update_canvas.jsp").forward(request, response);
                } catch (NumberFormatException e) {
                    request.getRequestDispatcher("/WEB-INF/views/error/500.jsp").forward(request, response);
                }
            } else {
                request.getRequestDispatcher("/WEB-INF/views/error/500.jsp").forward(request, response);
            }
        } else if ("/canvas/edit.do".equals(request.getServletPath())) {
            try {
                ServletFileUpload fileUpload = new ServletFileUpload(new DiskFileItemFactory());
                List<FileItem> fileItems = fileUpload.parseRequest(request);
                Canvas canvas = new Canvas(new Date());
                for (FileItem item:
                        fileItems) {
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
                        canvas.setSmallImg(item.get());
                    }
                }
                canvasService.updateCanvas(canvas);
                response.sendRedirect("/canvas/list.do");
            } catch (FileUploadException e) {
                e.printStackTrace();
            }
        } else if ("/canvas/delete.do".equals(request.getServletPath())) {
            String idStr = request.getParameter("id");
            if (StringUtils.isNotEmpty(idStr)) {
                try {
                    canvasService.deleteCanvas(Long.parseLong(idStr));
                    response.sendRedirect("/canvas/list.do");
                } catch (NumberFormatException e) {
                    request.getRequestDispatcher("/WEB-INF/views/error/500.jsp").forward(request, response);
                }
            } else {
                request.getRequestDispatcher("/WEB-INF/views/error/500.jsp").forward(request, response);
            }
        }
    }

    @Override
    public void destroy() {
        canvasService = null;
        categoryService = null;
    }
}
