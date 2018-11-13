package com.imooc.study.servlet;

import com.imooc.study.entity.Canvas;
import com.imooc.study.entity.Category;
import com.imooc.study.serivce.CanvasService;
import com.imooc.study.serivce.CategoryService;
import com.imooc.study.util.ParserRequest;
import com.imooc.study.utils.FileUtil;
import com.imooc.study.utils.StringUtils;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;


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
                    return;
                }
            }

            // 判断类别是否为空，为空就获取所有的油画和油画数，否则获取目标类别的油画和油画数
            if (StringUtils.isNotEmpty(category)) {
                canvas = canvasService.findCanvasByCategory(category, (page - 1) * 5, 5);
                canvasCount = canvasService.countCanvas(category);
            } else {
                canvas = canvasService.findCanvas((page - 1) * 5, 5);
                canvasCount = canvasService.countCanvas();
            }

            // 计算总共的页面
            int totalPage = canvasCount % 5 == 0 ? canvasCount / 5 : canvasCount / 5 + 1;

            request.setAttribute("category", category);
            request.setAttribute("totalPage", totalPage);
            request.setAttribute("canvases", canvas);
            request.setAttribute("page", page);


            /* 计算和设置类别相关的参数 */

            List<Category> categories = categoryService.findCategories();
            request.setAttribute("categories", categories);

            request.getRequestDispatcher("/WEB-INF/views/biz/canvas_list.jsp").forward(request, response);
        }
        // 跳转添加页面
        else if ("/canvas/addPrompt.do".equals(request.getServletPath())) {
            List<Category> categories = categoryService.findCategories();
            request.setAttribute("categories", categories);  // 将分类传给前端，用于显示所有可选分类
            request.getRequestDispatcher("/WEB-INF/views/biz/add_canvas.jsp").forward(request, response);
        }
        // 新建油画
        else if ("/canvas/add.do".equals(request.getServletPath())) {
            // 获取提交的数据
            Map<String, String> map = ParserRequest.fileUpload(request);
            // 如果提交的数据不存在或者存在空或null
            if (null == map || map.values().contains(null) || map.values().contains("")) {
                response.sendRedirect("/canvas/addPrompt.do");
                return;
            }

            // 添加油画需要的字段
            Set<String> nameSet = new HashSet<>();
            nameSet.add("name");
            nameSet.add("category");
            nameSet.add("price");
            nameSet.add("smallImg");
            nameSet.add("description");
            nameSet.add("details");

            // 如果缺少字段，则页面跳转至新建页面
            if (!map.keySet().containsAll(nameSet)){
                response.sendRedirect("/canvas/addPrompt.do");
                return;
            }

            // 从提交结果中提取需要的参数，构造canvas对象
            Canvas canvas = new Canvas();
            canvas.setName(map.get("name"));
            canvas.setCategory(map.get("category"));
            canvas.setPrice(Integer.parseInt(map.get("price")));
            canvas.setSmallImg(map.get("smallImg"));
            canvas.setCreateTime(new Date());
            canvas.setUpdateTime(new Date());
            canvas.setCreator((String) request.getSession().getAttribute("username"));
            canvas.setDetails(map.get("details"));
            canvas.setDescription(map.get("description"));

            // 执行添加油画操作，页面返回至油画展示页面
            canvasService.addCanvas(canvas);
            response.sendRedirect("/canvas/list.do");
        }
        // 跳转至编辑油画页面
        else if ("/canvas/editPrompt.do".equals(request.getServletPath())) {
            String idStr = request.getParameter("id");
            if (StringUtils.isNotEmpty(idStr)) {
                try {
                    Canvas canvas = canvasService.findCanvasById(Long.parseLong(idStr));
                    List<Category> categories = categoryService.findCategories();
                    // 向前端传入油画和所有分类
                    request.setAttribute("canvas", canvas);
                    request.setAttribute("categories", categories);
                    request.getRequestDispatcher("/WEB-INF/views/biz/update_canvas.jsp").forward(request, response);
                } catch (NumberFormatException e) {
                    request.getRequestDispatcher("/WEB-INF/views/error/500.jsp").forward(request, response);
                }
            } else {
                request.getRequestDispatcher("/WEB-INF/views/error/500.jsp").forward(request, response);
            }
        }
        // 编辑油画
        else if ("/canvas/edit.do".equals(request.getServletPath())) {
            Map<String, String> map = ParserRequest.fileUpload(request);
            if (null == map) {
                response.sendRedirect("/canvas/addPrompt.do");
                return;
            }

            // 编辑油画需要的字段
            Set<String> nameSet = new HashSet<>();
            nameSet.add("name");
            nameSet.add("category");
            nameSet.add("price");
            nameSet.add("smallImg");
            nameSet.add("description");
            nameSet.add("details");

            // 如果提交的字段中没有id，则报500错误
            if (!map.keySet().contains("id")) {
                request.getRequestDispatcher("/WEB-INF/views/error/500.jsp").forward(request, response);
                return;
            }

            // 如果缺少字段，则页面跳转至编辑页面
            if (!map.keySet().containsAll(nameSet)){
                response.sendRedirect("/canvas/editPrompt.do?id="+map.get("id"));
                return;
            }
            System.out.println(map);
            // 从map提取数据，构造canvas
            Canvas canvas = new Canvas();
            canvas.setId(Long.parseLong(map.get("id")));
            canvas.setUpdateTime(new Date());
            if (!"".equals(map.get("name")))
                canvas.setName(map.get("name"));
            if (!"".equals(map.get("category")))
                canvas.setCategory(map.get("category"));
            if (!"".equals(map.get("smallImg")))
                canvas.setSmallImg(map.get("smallImg"));
            if (!"".equals(map.get("price")))
                canvas.setPrice(Integer.parseInt(map.get("price")));
            if (!"".equals(map.get("description")))
                canvas.setDescription(map.get("description"));
            if (!"".equals(map.get("details")))
                canvas.setDetails(map.get("details"));
            System.out.println(canvas);

            // 执行更新操作
            canvasService.updateCanvas(canvas);

            response.sendRedirect("/canvas/list.do");
        }
        // 删除油画
        else if ("/canvas/delete.do".equals(request.getServletPath())) {
            String idStr = request.getParameter("id");
            if (StringUtils.isNotEmpty(idStr)) {
                try {
                    // 删除图片
                    FileUtil.deletePicById(Long.parseLong(idStr), canvasService, request);
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
