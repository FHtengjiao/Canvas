package com.imooc.study.servlet;

import com.imooc.study.entity.Canvas;
import com.imooc.study.entity.Category;
import com.imooc.study.serivce.CanvasService;
import com.imooc.study.serivce.CategoryService;
import com.imooc.study.utils.StringUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "CanvasServlet", urlPatterns = {
        "/canvas/list.do",
        "/canvas/getImg.do",
        "/canvas/detail.do"
})
public class CanvasServlet extends HttpServlet {

    private CanvasService canvasService;
    private CategoryService categoryService;

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if ("/canvas/list.do".equals(request.getServletPath())) {
            /* 计算和设置油画相关的参数 */
            // 获取参数，当前页数和类别
            String pageStr = request.getParameter("page");
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
            if (StringUtils.isNotEmpty(category)) {
                canvas = canvasService.findCanvasByCategory(category, (page - 1) * 3, 3);
                canvasCount = canvasService.countCanvas(category);
            } else {
                canvas = canvasService.findCanvas((page - 1) * 3, 3);
                canvasCount = canvasService.countCanvas();
            }

            // 计算总共的页面
            int totalPage = canvasCount % 3 == 0 ? canvasCount / 3 : canvasCount / 3 + 1;

            request.setAttribute("category", category);
            request.setAttribute("totalPage", totalPage);
            request.setAttribute("canvases", canvas);
            request.setAttribute("page", page);


            /* 计算和设置类别相关的参数 */

            List<Category> categories = categoryService.findCategories();
            request.setAttribute("categories", categories);

            request.getRequestDispatcher("/WEB-INF/views/biz/index.jsp").forward(request, response);
        } else if ("/canvas/detail.do".equals(request.getServletPath())) {
            String id = request.getParameter("id");
            Canvas canvas = canvasService.findCanvasById(Long.parseLong(id));
            request.setAttribute("canvas", canvas);
            request.getRequestDispatcher("/WEB-INF/views/biz/detail.jsp").forward(request, response);
        }
    }

    @Override
    public void destroy() {
        canvasService = null;
        categoryService = null;
    }

    @Override
    public void init() throws ServletException {
        canvasService = new CanvasService();
        categoryService = new CategoryService();
    }
}
