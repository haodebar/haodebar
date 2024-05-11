package com.chaoyue.haodebar.config;

import com.chaoyue.haodebar.Interceptor.IpAccessInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.annotation.Resource;

@Configuration
public class WebMvcConfig  implements WebMvcConfigurer {
    @Resource
    private IpAccessInterceptor ipAccessInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {

        registry.addInterceptor(ipAccessInterceptor).addPathPatterns("/**");
    }
}
