package org.pac4j.demo.spring.config;

import org.pac4j.springframework.web.RequiresAuthenticationInterceptor;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import org.pac4j.core.config.Config;

import javax.inject.Inject;

@Configuration
@ComponentScan(basePackages = "org.pac4j.springframework.web")
public class SecurityConfig extends WebMvcConfigurerAdapter {

    @Inject
    private Config config;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new RequiresAuthenticationInterceptor(config, "CasClient")).addPathPatterns("/cas/*");
    }
}