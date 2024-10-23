package com.estsoft.springproject.filter;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;

import java.io.IOException;

public class FirstFilter implements Filter {
    @Override
    public void init(jakarta.servlet.FilterConfig filterConfig) throws ServletException {
        System.out.println("First Filter.Init()");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        System.out.println("First Filter.doFilter() request");
        // requestURI
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        System.out.println("request.getRequestURI() = " + request.getRequestURI());

        filterChain.doFilter(servletRequest, servletResponse);
        System.out.println("First Filter.doFilter() response");


    }

    @Override
    public void destroy() {
        Filter.super.destroy();
        System.out.println("First Filter.destroy()");
    }
}
