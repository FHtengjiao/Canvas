package com.imooc.study.servlet;

import com.imooc.study.entity.Category;
import com.imooc.study.serivce.CategoryService;
import com.imooc.study.utils.StringUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;
import java.util.List;


@WebServlet(name = "CanvasCategoryServlet", urlPatterns = {
        "/canvasCategory/list.do",
        "/canvasCategory/editPrompt.do",
        "/canvasCategory/edit.do",
        "/canvasCategory/delete.do",
        "/canvasCategory/addPrompt.do",
        "/canvasCategory/add.do"
})
public class CanvasCategoryServlet extends HttpServlet {

    private CategoryService categoryService;

    @Override
    public void init() throws ServletException {
        categoryService = new CategoryService();
    }

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 展示所有的分类
        if ("/canvasCategory/list.do".equals(request.getServletPath())) {
            List<Category> categories = categoryService.findCategories();
            request.setAttribute("categories", categories);
            request.getRequestDispatcher("/WEB-INF/views/biz/category_list.jsp").forward(request, response);
        }
        // 跳转编辑分类页面
        else if ("/canvasCategory/editPrompt.do".equals(request.getServletPath())) {
            String idStr = request.getParameter("id");
            if (StringUtils.isNotEmpty(idStr)) {
                try {
                    Category category = categoryService.findCategoryById(Long.parseLong(idStr));
                    request.setAttribute("category", category);
                    request.getRequestDispatcher("/WEB-INF/views/biz/update_category.jsp").forward(request, response);
                } catch (NumberFormatException e) {
                    request.getRequestDispatcher("/WEB-INF/views/error/500.jsp").forward(request, response);
                }
            } else {
                request.getRequestDispatcher("/WEB-INF/views/error/500.jsp").forward(request, response);
            }
        }
        // 编辑分类
        else if ("/canvasCategory/edit.do".equals(request.getServletPath())) {
            String id = request.getParameter("id");
            String name = request.getParameter("name");
            String description = request.getParameter("description");
            if (StringUtils.isNotEmpty("id")) {
                if (StringUtils.isNotEmpty(name) && StringUtils.isNotEmpty(description)) {
                    Category  category = null;
                    try {
                        category = new Category(Long.parseLong(id), name, new Date(), description);
                    } catch (NumberFormatException e) {
                        e.printStackTrace();
                    }
                    categoryService.updateCategory(category);
                    response.sendRedirect("/canvasCategory/list.do");
                } else
                    request.getRequestDispatcher("/canvasCategory/editPrompt.do?id=" + Integer.parseInt(id)).forward(request, response);
            } else
                request.getRequestDispatcher("/WEB-INF/views/error/500.jsp").forward(request, response);
        }
        // 删除分类
        else if ("/canvasCategory/delete.do".equals(request.getServletPath())) {
            String idStr = request.getParameter("id");
            if (StringUtils.isNotEmpty(idStr)) {
                try {
                    categoryService.deleteCategory(Long.parseLong(idStr));
                    response.sendRedirect("/canvasCategory/list.do");
                } catch (NumberFormatException e) {
                    request.getRequestDispatcher("/WEB-INF/views/error/500.jsp").forward(request, response);
                }
            } else {
                request.getRequestDispatcher("/WEB-INF/views/error/500.jsp").forward(request, response);
            }
        }
        // 跳转至添加分类的页面
        else if ("/canvasCategory/addPrompt.do".equals(request.getServletPath())) {
            request.getRequestDispatcher("/WEB-INF/views/biz/add_category.jsp").forward(request, response);
        }
        // 添加分类
        else if ("/canvasCategory/add.do".equals(request.getServletPath())) {
            String name = request.getParameter("name");
            String description = request.getParameter("description");
            if (StringUtils.isNotEmpty(name) && StringUtils.isNotEmpty(description)) {
                Category  category = new Category(name, new Date(), new Date(), description);
                categoryService.addCategory(category);
                response.sendRedirect("/canvasCategory/list.do");
            } else
                response.sendRedirect("/canvasCategory/addPrompt.do");
        }
    }

    @Override
    public void destroy() {
        super.destroy();
        categoryService = null;
    }
}
