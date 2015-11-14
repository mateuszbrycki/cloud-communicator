package org.pac4j.demo.spring.config;

import org.pac4j.core.config.Config;
import org.pac4j.springframework.web.RequiresAuthenticationInterceptor;
import org.springframework.context.annotation.*;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;
import org.springframework.core.env.Environment;

import javax.inject.Inject;

@EnableWebMvc
@Configuration
@ComponentScan({"org.pac4j", "org.pac4j.springframework.web" })
@PropertySource(value = { "classpath:application.properties"})
public class AppConfig extends WebMvcConfigurerAdapter {

    @Inject
    private Environment environment;

    @Inject
    private Config config;

    @Bean
    public InternalResourceViewResolver viewResolver() {
        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
        viewResolver.setViewClass(JstlView.class);
        viewResolver.setPrefix("/WEB-INF/views/");
        viewResolver.setSuffix(".jsp");
        return viewResolver;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new RequiresAuthenticationInterceptor(config, "CasClient", "user")).addPathPatterns("/cas/*");
        registry.addInterceptor(new RequiresAuthenticationInterceptor(config, "CasClient", "admin")).addPathPatterns("/admin/*");
    }
}