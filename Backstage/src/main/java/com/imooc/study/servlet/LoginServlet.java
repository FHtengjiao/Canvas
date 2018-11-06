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
            if (StringUtils.isNotEmpty(userName) && userName.equals(password)) {
                request.getSession().setAttribute("username", userName);
                response.sendRedirect("/canvas/list.do");
            } else {
                response.sendRedirect("/loginPrompt.do");
            }
        } else if ("/loginPrompt.do".equals(request.getServletPath())) {
            request.getRequestDispatcher("WEB-INF/views/biz/login.jsp").forward(request, response);
        }
    }
}
