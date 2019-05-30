package com.everis.springboot.di.app;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


import com.everis.springboot.di.app.models.service.ComplexService;
import com.everis.springboot.di.app.models.service.IService;
import com.everis.springboot.di.app.models.service.Service;

@Configuration
public class AppConfig {
    
    @Bean("SimpleService")
    public IService serviceRegistration() {
	
	return new Service();
    }
    
    @Bean("complexService")
    public IService complexServiceRegistration() {
	
	return new ComplexService();
    }

}
