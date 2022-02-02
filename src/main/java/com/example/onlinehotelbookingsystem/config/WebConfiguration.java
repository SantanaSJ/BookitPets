package com.example.onlinehotelbookingsystem.config;

import com.example.onlinehotelbookingsystem.web.Interceptor.StatsInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfiguration implements WebMvcConfigurer {

    private final StatsInterceptor statsInterceptor;

    public WebConfiguration(StatsInterceptor statsInterceptor) {
        this.statsInterceptor = statsInterceptor;
    }


    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(statsInterceptor);
    }
}
