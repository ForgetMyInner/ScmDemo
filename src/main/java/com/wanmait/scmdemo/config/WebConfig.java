package com.wanmait.scmdemo.config;

import com.wanmait.scmdemo.interceptor.JWTInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.annotation.Resource;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    //@Resource
    //private DemoInterceptor demoInterceptor;

    @Resource
    private JWTInterceptor jwtInterceptor;
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                //只允许下面这两个域可以进行跨域请求
                //.allowedOrigins("http://www.wanmait.com","http://www.qingruan.net")
                .allowedOrigins("*")
                //.allowedMethods("GET","POST","DELETE","PUT","OPTIONS")
                .allowedMethods("*")
                .allowedHeaders("*")
                //是否允许传递凭证，默认false,前后端分离时，不能传送cookie，session也就用不了
                //如果设为true，可以传送cookie，前端axios要配合设置：xhr.withCredentials=true
//                .allowCredentials(true)
                //预检请求的缓存时间，2小时之内如果发送复杂请求不用先发预检请求
                .maxAge(60*60*2);
    }

    @Override

    public void addInterceptors(InterceptorRegistry registry) {
        //registry.addInterceptor(demoInterceptor).addPathPatterns("/manage/**").excludePathPatterns("/manage/login","/manage/test");
        registry.addInterceptor(jwtInterceptor).addPathPatterns("/manage/**").excludePathPatterns("/manage/login");
    }

    //配置静态资源的访问
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        //http://localhost:8080/static/1.jpg -> claspath:static/1.jpg
        //http://localhost:8080/static/aaa/1.jpg -> claspath:static/aaa/1.jpg
        registry.addResourceHandler("/static/**").addResourceLocations("classpath:static/");

    }
}
