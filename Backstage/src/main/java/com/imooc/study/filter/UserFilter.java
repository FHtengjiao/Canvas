package com.imooc.study.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@WebFilter(filterName = "UserFilter", urlPatterns = "*.do")
public class UserFilter implements Filter {

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        String userName = (String) request.getSession().getAttribute("username");
        if ("/login.do".equals(request.getServletPath()) || "loginPrompt.do".equals(request.getServletPath())) {
            filterChain.doFilter(request, response);
        } else if (null != userName && !"".equals(userName)) {
            filterChain.doFilter(request, response);
        } else {
            response.sendRedirect("/loginPrompt.do");
        }
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void destroy() {

    }
}
