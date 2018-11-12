package com.imooc.study.servlet;

import com.imooc.study.utils.StringUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "LoginServlet", urlPatterns = {"/login.do", "/loginPrompt.do"})
public class LoginServlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if ("/login.do".equals(request.getServletPath())) {
            String userName = request.getParameter("username");
            String password = request.getParameter("password");
            // 当userName和password不为空且相同的情况下，允许用户登录
            if (StringUtils.isNotEmpty(userName) && userName.equals(password)) {
                request.getSession().setAttribute("username", userName);  //将用户名保存在session中
                response.sendRedirect("/canvas/list.do");
            } else {
                response.sendRedirect("/loginPrompt.do");
            }
        } else if ("/loginPrompt.do".equals(request.getServletPath())) {
            request.getRequestDispatcher("/WEB-INF/views/biz/login.jsp").forward(request, response);
        }
    }
}
