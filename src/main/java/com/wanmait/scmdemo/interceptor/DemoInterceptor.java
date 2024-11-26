package com.wanmait.scmdemo.interceptor;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class DemoInterceptor implements HandlerInterceptor {

    //调用功能处理方法handler之前调用，如果返回true则继续执行handler方法，返回false就不执行
    //如果返回false，如果前后端不分离一般配合重定向，如果前后端分离，直接给客户端返回一些信息
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        System.out.println("preHandle.................");
        return true;
    }

    //调用功能处理方法之后执行，可以对ModelAndView进行处理
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        System.out.println("postHandle..............");
    }
    //调用功能处理方法之后执行，视图已经渲染完成，可以进行一些清理工作
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        System.out.println("afterCompletion...............");
    }
}
