package com.dz.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MvcConfig implements WebMvcConfigurer {
    //这是为了解决静态资源找不到的问题
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        //所有访问/back/的都要去指定的位置下找资源
        registry.addResourceHandler("/back/**").addResourceLocations("classpath:/static/");
    }
}

