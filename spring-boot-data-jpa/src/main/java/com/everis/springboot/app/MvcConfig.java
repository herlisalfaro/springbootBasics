package com.everis.springboot.app;

import org.springframework.context.annotation.Configuration;

import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Method to upload client's picture to a root folder named "uploads"  
 * 
 *
 */

@Configuration
public class MvcConfig implements WebMvcConfigurer {

   /* private final Logger logger = LoggerFactory.getLogger(getClass());
    
   @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
	WebMvcConfigurer.super.addResourceHandlers(registry);
	String resourcePath = Paths.get("uploads").toAbsolutePath().toUri().toString();
	logger.info("resourcePath es: " + resourcePath);
	registry.addResourceHandler("/uploads/**")
	.addResourceLocations(resourcePath);
	
	
    }*/
    
    public void addViewControllers(ViewControllerRegistry registry) {
	registry.addViewController("/error_403").setViewName("error_403");
    }
    
    

}
