package com.xiaobo.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter("/LoginUI")
public class loginFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpSession w = ((HttpServletRequest) request).getSession();
        if ("on".equals(w.getAttribute("auto"))) {
            System.out.println("已勾选自动登录" + "--" + w.getAttribute("auto"));
            ((HttpServletRequest) request).getRequestDispatcher("/autoLoginFun").forward(request, response);
        } else {
            System.out.println("没有勾选自动登录" + "--" + w.getAttribute("auto"));
            chain.doFilter(request, response);
        }
    }

    @Override
    public void destroy() {

    }

}
