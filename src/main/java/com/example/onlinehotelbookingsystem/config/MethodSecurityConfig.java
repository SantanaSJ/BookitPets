package com.example.onlinehotelbookingsystem.config;

import com.example.onlinehotelbookingsystem.service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.expression.method.MethodSecurityExpressionHandler;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.method.configuration.GlobalMethodSecurityConfiguration;

@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class MethodSecurityConfig extends GlobalMethodSecurityConfiguration {

//    with constructor injection form a cycle
    @Autowired
    private AppMethodSecurityExpressionHandler appMethodSecurityExpressionHandler;

    @Override
    protected MethodSecurityExpressionHandler createExpressionHandler() {
        return appMethodSecurityExpressionHandler;
    }

    @Bean
    public AppMethodSecurityExpressionHandler createExpressionHandler(BookingService bookingService) {
        return new AppMethodSecurityExpressionHandler(bookingService);
    }
}
