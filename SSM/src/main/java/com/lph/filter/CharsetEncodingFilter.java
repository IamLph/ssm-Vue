package com.lph.filter;

import javax.servlet.*;
import java.io.IOException;

public class CharsetEncodingFilter implements Filter {

    private static String encoding; // 定义变量接收初始化的值

    @Override
    public void init(FilterConfig config) throws ServletException {
        // 接收web.xml配置文件中的初始参数
        encoding = config.getInitParameter("CharsetEncoding");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        // 设置字符编码链锁
        request.setCharacterEncoding(encoding);
        response.setCharacterEncoding(encoding);
        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {

    }
}
