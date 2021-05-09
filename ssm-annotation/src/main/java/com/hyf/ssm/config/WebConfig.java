package com.hyf.ssm.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.config.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

/**
 * @author baB_hyf
 * @date 2020/05/17
 */
@EnableWebMvc
@Configuration
@ComponentScan(
        value = "com.hyf.ssm",
        useDefaultFilters = false,
        includeFilters = @ComponentScan.Filter(Controller.class)
)
public class WebConfig implements WebMvcConfigurer {

    // TODO enable-matrix-variables="true"

    @Override
    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
        RequestMappingHandlerMapping mapping = new RequestMappingHandlerMapping();
        mapping.setRemoveSemicolonContent(false);
        configurer.enable();
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/pages/**").addResourceLocations("/pages/");
    }

    @Override
    public void configureViewResolvers(ViewResolverRegistry registry) {
        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
        viewResolver.setPrefix("/pages/");
        viewResolver.setSuffix(".html");
        registry.viewResolver(viewResolver);
    }

}
