package com.zlei.gp.viewconfigurer;

import com.zlei.gp.component.MyLocaleResolver;
import com.zlei.gp.interceptor.LoginHandlerInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @ClassName: ViewConfigurer
 * @Description:
 * @Date: 2020-01-14 15:26
 * @Author: ZhangLei
 * version: 1.0
 **/
@Configuration
public class ViewConfigurer {

    @Bean
    public WebMvcConfigurer webMvcConfigurer() {
        return new WebMvcConfigurer() {
            // 添加视图控制
            @Override
            public void addViewControllers(ViewControllerRegistry registry) {
                registry.addViewController("/").setViewName("main/login");
                registry.addViewController("/index.html").setViewName("main/login");
                registry.addViewController("/main.html").setViewName("main/index");
                registry.addViewController("/register.html").setViewName("main/register");
            }

            @Override
            public void addInterceptors(InterceptorRegistry registry) {
                registry.addInterceptor(new LoginHandlerInterceptor())
                        //指定要拦截的请求 /** 表示拦截所有请求
                        .addPathPatterns("/**")
                        //排除不需要拦截的请求路径
                        .excludePathPatterns("/", "/index.html", "/login", "/register.html", "/register")
                        //springboot2+之后需要将静态资源文件的访问路径 也排除
                        .excludePathPatterns("/css/*", "/img/*","/js/*");
            }
        };
    }

    /**
    * @Description: 区域解析器
    * @Param: []
    * @return: org.springframework.web.servlet.LocaleResolver
    * @Author: ZhangLei
    * @Date: 2020/1/15
    */
    @Bean
    public LocaleResolver localeResolver() {
        return new MyLocaleResolver();
    }

}
