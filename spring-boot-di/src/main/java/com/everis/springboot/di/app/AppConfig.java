package com.everis.springboot.di.app;

import java.util.Arrays;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.everis.springboot.di.app.models.domain.BillingItem;
import com.everis.springboot.di.app.models.domain.Product;
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
    
    @Bean("BillingItem")
    public List<BillingItem> itemsRegistration() {
	
	Product productOne = new Product("Authentic Poet Pen",100);
	Product productTwo = new Product("Really Creative Notebook",75);
	
	BillingItem lineOne = new BillingItem(productOne, 4);
	BillingItem lineTwo = new BillingItem(productTwo, 5);
	
	return Arrays.asList(lineOne, lineTwo);
    }

}
