package com.incon.connect.ui.config;

import java.util.HashSet;
import java.util.Set;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.thymeleaf.spring4.SpringTemplateEngine;
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver;
import org.thymeleaf.templateresolver.TemplateResolver;

@Configuration
public class MvcConfig extends WebMvcConfigurerAdapter {
	
	@Bean
	public TemplateResolver emailTemplateResolver() {
	    TemplateResolver resolver = new ClassLoaderTemplateResolver();
	    resolver.setPrefix("templates/");
	    resolver.setSuffix(".html");
	    resolver.setTemplateMode("HTML5");
	    resolver.setCharacterEncoding("UTF-8");
	    resolver.setOrder(1);
	    resolver.setCacheable(false);
	    return resolver;
	}

@Bean
	public SpringTemplateEngine templateEngine() {
	    final SpringTemplateEngine engine = new SpringTemplateEngine();
	    final Set<TemplateResolver> templateResolvers = new HashSet<TemplateResolver>();
	    templateResolvers.add(emailTemplateResolver());
	    engine.addDialect(new org.thymeleaf.extras.springsecurity4.dialect.SpringSecurityDialect());
	    engine.setTemplateResolvers(templateResolvers);
	    return engine;
	}
    
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/add-products").setViewName("add-products");
        registry.addViewController("/adduser").setViewName("add-users");
        registry.addViewController("/approveuser").setViewName("approve-users");
        registry.addViewController("/").setViewName("add-products");
        registry.addViewController("/hello").setViewName("hello");
        registry.addViewController("/email").setViewName("email");
        registry.addViewController("/login").setViewName("loginpage");
        registry.addViewController("/add-codes").setViewName("add-codes");
        registry.addViewController("/add-notification").setViewName("add-notification");
        registry.addViewController("/reports").setViewName("reports");
        registry.addViewController("/gencode").setViewName("gencode");
        registry.addViewController("/offers").setViewName("offers");
        registry.addViewController("/modalDialog").setViewName("modalDialog");
        registry.addViewController("/configurations").setViewName("configurations");
        registry.addViewController("/logout").setViewName("/login");
        registry.addViewController("/menuoptions").setViewName("menu-options");
    }

}
